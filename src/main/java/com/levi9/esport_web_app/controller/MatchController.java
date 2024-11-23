package com.levi9.esport_web_app.controller;

import com.levi9.esport_web_app.dto.MatchRequest;
import com.levi9.esport_web_app.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/matches")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@PostMapping
	public ResponseEntity<?> addMatch(@Valid @RequestBody MatchRequest matchRequest) {
		try {
			return ResponseEntity.ok(matchService.addMatch(matchRequest));
		} catch (IllegalArgumentException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
}