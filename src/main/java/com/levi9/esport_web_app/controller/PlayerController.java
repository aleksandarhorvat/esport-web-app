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

import com.levi9.esport_web_app.dto.PlayerRequest;
import com.levi9.esport_web_app.dto.PlayerResponse;
import com.levi9.esport_web_app.service.PlayerService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@GetMapping
	public ResponseEntity<List<PlayerResponse>> getAllPlayers() {
		return ResponseEntity.ok(playerService.getAllPlayers());
	}

	@PostMapping("/create")
	public ResponseEntity<?> createPlayer(@Valid @RequestBody PlayerRequest playerRequest) {
		try {
			return ResponseEntity.ok(playerService.createPlayer(playerRequest));
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPlayerById(@PathVariable UUID id) {
		try {
			return ResponseEntity.ok(playerService.getPlayerById(id));
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(404).body(ex.getMessage());
		}
	}
}