package com.levi9.esport_web_app.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levi9.esport_web_app.dto.PlayerRequest;
import com.levi9.esport_web_app.dto.PlayerResponse;
import com.levi9.esport_web_app.model.Player;
import com.levi9.esport_web_app.repository.PlayerRepository;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;

	@GetMapping
	public ResponseEntity<List<PlayerResponse>> getAllPlayers() {
		// Retrieve all players
		List<Player> players = playerRepository.findAll();

		// Map to response DTOs
		List<PlayerResponse> playerResponses = players.stream().map(PlayerResponse::new).collect(Collectors.toList());

		// Return the list
		return ResponseEntity.ok(playerResponses);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createPlayer(@Valid @RequestBody PlayerRequest playerRequest) {
		// Check if the nickname is already in use
		if (playerRepository.existsByNickname(playerRequest.getNickname())) {
			return ResponseEntity.badRequest()
					.body("Nickname '" + playerRequest.getNickname() + "' is already in use.");
		}

		// Create a new Player entity
		Player player = new Player();
		player.setId(UUID.randomUUID()); // Generate a new UUID
		player.setNickname(playerRequest.getNickname());
		player.setWins(0);
		player.setLosses(0);
		player.setElo(0);
		player.setHoursPlayed(0);
		player.setTeam(null);
		player.setRatingAdjustment(0);

		// Save the player
		player = playerRepository.save(player);

		// Return the response
		return ResponseEntity.ok(new PlayerResponse(player));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPlayerById(@PathVariable UUID id) {
		Optional<Player> playerOptional = playerRepository.findById(id);

		if (playerOptional.isEmpty()) {
			return ResponseEntity.status(404).body("Player not found.");
		}

		Player player = playerOptional.get();
		return ResponseEntity.ok(new PlayerResponse(player));
	}
}