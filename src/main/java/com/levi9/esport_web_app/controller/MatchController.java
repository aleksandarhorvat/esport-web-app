package com.levi9.esport_web_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.esport_web_app.dto.MatchRequest;
import com.levi9.esport_web_app.model.Match;
import com.levi9.esport_web_app.model.MatchId;
import com.levi9.esport_web_app.model.Player;
import com.levi9.esport_web_app.model.Team;
import com.levi9.esport_web_app.repository.MatchRepository;
import com.levi9.esport_web_app.repository.PlayerRepository;
import com.levi9.esport_web_app.repository.TeamRepository;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/matches")
public class MatchController {

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@PostMapping
	public ResponseEntity<?> addMatch(@Valid @RequestBody MatchRequest matchRequest) {
		// Validate duration
		if (matchRequest.getDuration() < 1) {
			return ResponseEntity.badRequest().body("Duration must be at least 1 hour.");
		}

		// Validate teams
		Team team1 = teamRepository.findById(matchRequest.getTeam1Id()).orElse(null);
		Team team2 = teamRepository.findById(matchRequest.getTeam2Id()).orElse(null);

		if (team1 == null || team2 == null) {
			return ResponseEntity.badRequest().body("One or both teams not found.");
		}

		// Validate winning team
		Team winningTeam = null;
		if (matchRequest.getWinningTeamId() != null) {
			winningTeam = teamRepository.findById(matchRequest.getWinningTeamId()).orElse(null);
			if (winningTeam == null || (!winningTeam.equals(team1) && !winningTeam.equals(team2))) {
				return ResponseEntity.badRequest().body("Winning team must be either team1 or team2.");
			}
		}

		// Create and save the match
		MatchId matchId = new MatchId();
		matchId.setTeam1Id(team1.id);
		matchId.setTeam2Id(team2.id);
		Match match = new Match();
		match.setId(matchId);
		match.setWinningTeamId(winningTeam.id);
		match.setDuration(matchRequest.getDuration());
		match = matchRepository.save(match);

		// Update player stats for both teams
		updatePlayerStats(team1, team2, matchRequest.getDuration(), winningTeam);
		updatePlayerStats(team2, team1, matchRequest.getDuration(), winningTeam);

		// Save updated players
		playerRepository.saveAll(team1.getPlayers());
		playerRepository.saveAll(team2.getPlayers());

		// Return the response
		return ResponseEntity.status(200).body("OK");
	}

	private void updatePlayerStats(Team team, Team opposingTeam, int duration, Team winningTeam) {
		for (Player player : team.getPlayers()) {
			// Update hours played
			int updatedHours = player.getHoursPlayed() + duration;
			player.setHoursPlayed(updatedHours);

			// Determine K value based on updated hours
			int k = getRatingAdjustment(updatedHours);
			player.setRatingAdjustment(k);

			// Calculate R2 (average ELO of the opposing team)
			double r2 = calculateAverageElo(opposingTeam);

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