package com.levi9.esport_web_app.service;

import com.levi9.esport_web_app.dto.PlayerRequest;
import com.levi9.esport_web_app.dto.PlayerResponse;
import com.levi9.esport_web_app.model.Player;
import com.levi9.esport_web_app.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	public List<PlayerResponse> getAllPlayers() {
		// Retrieve all players
		List<Player> players = playerRepository.findAll();
		// Map to response DTOs and return the list
		return players.stream().map(PlayerResponse::new).collect(Collectors.toList());
	}

	public PlayerResponse createPlayer(PlayerRequest playerRequest) {
		// Check if the nickname is already in use
		if (playerRepository.existsByNickname(playerRequest.getNickname())) {
			throw new IllegalArgumentException("Nickname '" + playerRequest.getNickname() + "' is already in use.");
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
		Player savedPlayer = playerRepository.save(player);

		// Return the response
		return new PlayerResponse(savedPlayer);
	}

	public PlayerResponse getPlayerById(UUID id) {
		Player player = playerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Player not found."));
		return new PlayerResponse(player);
	}
}