package com.levi9.esport_web_app.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.levi9.esport_web_app.dto.TeamRequest;
import com.levi9.esport_web_app.dto.TeamResponse;
import com.levi9.esport_web_app.model.Player;
import com.levi9.esport_web_app.model.Team;
import com.levi9.esport_web_app.repository.PlayerRepository;
import com.levi9.esport_web_app.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	public TeamResponse createTeam(TeamRequest teamRequest) {
		// Validate team name uniqueness
		if (teamRepository.existsByTeamName(teamRequest.getTeamName())) {
			throw new IllegalArgumentException("Team name '" + teamRequest.getTeamName() + "' is already in use.");
		}

		// Validate the number of players
		if (teamRequest.getPlayers().size() != 5) {
			throw new IllegalArgumentException("A team must have exactly 5 players.");
		}

		// Validate players
		List<Player> players = playerRepository.findAllById(teamRequest.getPlayers());
		if (players.size() != 5) {
			throw new IllegalArgumentException("One or more players not found.");
		}

		// Validate that players are not already part of a team
		for (Player player : players) {
			if (player.getTeam() != null) {
				throw new IllegalArgumentException("Player '" + player.getNickname() + "' is already part of a team.");
			}
		}

		// Create and save the team
		Team team = new Team();
		team.setId(UUID.randomUUID());
		team.setTeamName(teamRequest.getTeamName());
		team = teamRepository.save(team);

		// Assign the team to the players and save them
		for (Player player : players) {
			player.setTeam(team);
		}
		playerRepository.saveAll(players);

		team.setPlayers(players);

		// Return a response with the created team
		return new TeamResponse(team);
	}

	public TeamResponse getTeamById(UUID id) {
		Team team = teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found."));
		return new TeamResponse(team);
	}
}