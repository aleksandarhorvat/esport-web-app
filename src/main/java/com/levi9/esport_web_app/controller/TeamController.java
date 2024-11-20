package com.levi9.esport_web_app.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.esport_web_app.dto.TeamRequest;
import com.levi9.esport_web_app.dto.TeamResponse;
import com.levi9.esport_web_app.model.Player;
import com.levi9.esport_web_app.model.Team;
import com.levi9.esport_web_app.repository.PlayerRepository;
import com.levi9.esport_web_app.repository.TeamRepository;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/teams")
public class TeamController {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@PostMapping
	public ResponseEntity<?> createTeam(@Valid @RequestBody TeamRequest teamRequest) {
		// Validate team name uniqueness
		if (teamRepository.existsByTeamName(teamRequest.getTeamName())) {
			return ResponseEntity.badRequest().body("Team name '" + teamRequest.getTeamName() + "' is already in use.");
		}

		// Validate the number of players
		if (teamRequest.getPlayers().size() != 5) {
			return ResponseEntity.badRequest().body("A team must have exactly 5 players.");
		}

		// Validate players
		List<Player> players = playerRepository.findAllById(teamRequest.getPlayers());
		if (players.size() != 5) {
			return ResponseEntity.badRequest().body("One or more players not found.");
		}

		// Validate that players are not already part of a team
		for (Player player : players) {
			if (player.getTeam() != null) {
				return ResponseEntity.badRequest()
						.body("Player '" + player.getNickname() + "' is already part of a team.");
			}
		}

		// Create the team
		Team team = new Team();
		team.setId(UUID.randomUUID()); // Set the ID
		team.setTeamName(teamRequest.getTeamName());

		// Save the team first so it has a valid ID
		team = teamRepository.save(team);

		// Now assign the team to the players
		for (Player player : players) {
			player.setTeam(team);
		}

		// Save the players after the team has been saved
		playerRepository.saveAll(players);

		team.setPlayers(players);

		// Refetch the team to ensure it's loaded with players
		team = teamRepository.save(team);

		// Return the team response with updated players
		return ResponseEntity.ok(new TeamResponse(team));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTeamById(@PathVariable UUID id) {
		// Fetch the team by ID
		Team team = teamRepository.findById(id).orElse(null);

		// If the team does not exist, return a 404 response
		if (team == null) {
			return ResponseEntity.status(404).body("Team not found");
		}

		// Return the team along with its players
		TeamResponse teamResponse = new TeamResponse(team);
		return ResponseEntity.ok(teamResponse);
	}

}