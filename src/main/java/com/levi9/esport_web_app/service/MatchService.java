package com.levi9.esport_web_app.service;

import com.levi9.esport_web_app.dto.MatchRequest;
import com.levi9.esport_web_app.model.Match;
import com.levi9.esport_web_app.model.MatchId;
import com.levi9.esport_web_app.model.Player;
import com.levi9.esport_web_app.model.Team;
import com.levi9.esport_web_app.repository.MatchRepository;
import com.levi9.esport_web_app.repository.PlayerRepository;
import com.levi9.esport_web_app.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	public String addMatch(MatchRequest matchRequest) {
		// Validate duration
		if (matchRequest.getDuration() < 1) {
			throw new IllegalArgumentException("Duration must be at least 1 hour.");
		}

		// Validate teams
		Team team1 = teamRepository.findById(matchRequest.getTeam1Id())
				.orElseThrow(() -> new IllegalArgumentException("Team 1 not found."));
		Team team2 = teamRepository.findById(matchRequest.getTeam2Id())
				.orElseThrow(() -> new IllegalArgumentException("Team 2 not found."));

		// Validate winning team
		Team winningTeam = null;
		if (matchRequest.getWinningTeamId() != null) {
			winningTeam = teamRepository.findById(matchRequest.getWinningTeamId())
					.orElseThrow(() -> new IllegalArgumentException("Winning team not found."));
			if (!winningTeam.equals(team1) && !winningTeam.equals(team2)) {
				throw new IllegalArgumentException("Winning team must be either team1 or team2.");
			}
		}

		// Create and save the match
		MatchId matchId = new MatchId();
		matchId.setTeam1Id(team1.getId());
		matchId.setTeam2Id(team2.getId());
		Match match = new Match();
		match.setId(matchId);
		match.setWinningTeamId(winningTeam != null ? winningTeam.getId() : null);
		match.setDuration(matchRequest.getDuration());
		match = matchRepository.save(match);

		// Calculate R2 (average ELO of both teams)
		double avgEloT1 = calculateAverageElo(team1);
		double avgEloT2 = calculateAverageElo(team2);

		// Update player stats for both teams
		updatePlayerStats(team1, avgEloT2, matchRequest.getDuration(), winningTeam);
		updatePlayerStats(team2, avgEloT1, matchRequest.getDuration(), winningTeam);

		// Save updated players
		playerRepository.saveAll(team1.getPlayers());
		playerRepository.saveAll(team2.getPlayers());

		return "OK";
	}

	private void updatePlayerStats(Team team, double r2, int duration, Team winningTeam) {
		for (Player player : team.getPlayers()) {
			// Update hours played
			int updatedHours = player.getHoursPlayed() + duration;
			player.setHoursPlayed(updatedHours);

			// Determine K value based on updated hours
			int k = getRatingAdjustment(updatedHours);
			player.setRatingAdjustment(k);

			// Calculate expected score (E)
			double e = 1.0 / (1.0 + Math.pow(10.0, (r2 - player.getElo()) / 400.0));

			// Determine actual score (S)
			double s = (winningTeam == null) ? 0.5 : (winningTeam.equals(team) ? 1.0 : 0.0);

			// Update ELO
			double updatedElo = player.getElo() + k * (s - e);
			int newElo = (int) Math.round(updatedElo);
			player.setElo(newElo);

			// Update wins/losses
			if (winningTeam != null) {
				if (winningTeam.equals(team)) {
					player.setWins(player.getWins() + 1);
				} else {
					player.setLosses(player.getLosses() + 1);
				}
			}
		}
	}

	private double calculateAverageElo(Team team) {
		return team.getPlayers().stream().mapToInt(Player::getElo).average().orElse(0);
	}

	private int getRatingAdjustment(int hoursPlayed) {
		if (hoursPlayed < 500) {
			return 50;
		} else if (hoursPlayed < 1000) {
			return 40;
		} else if (hoursPlayed < 3000) {
			return 30;
		} else if (hoursPlayed < 5000) {
			return 20;
		} else {
			return 10;
		}
	}
}