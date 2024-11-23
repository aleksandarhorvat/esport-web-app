package com.levi9.esport_web_app.controller;

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
import com.levi9.esport_web_app.service.TeamService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/teams")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@PostMapping
	public ResponseEntity<?> createTeam(@Valid @RequestBody TeamRequest teamRequest) {
		try {
			return ResponseEntity.ok(teamService.createTeam(teamRequest));
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTeamById(@PathVariable UUID id) {
		try {
			return ResponseEntity.ok(teamService.getTeamById(id));
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.status(404).body(ex.getMessage());
		}
	}
}