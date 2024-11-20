package com.levi9.esport_web_app.dto;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class TeamRequest {

	@NotBlank(message = "Team name cannot be empty")
	private String teamName;

	@NotEmpty(message = "Players list cannot be empty")
	private List<UUID> players;

	// Getters and Setters
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<UUID> getPlayers() {
		return players;
	}

	public void setPlayers(List<UUID> players) {
		this.players = players;
	}

}