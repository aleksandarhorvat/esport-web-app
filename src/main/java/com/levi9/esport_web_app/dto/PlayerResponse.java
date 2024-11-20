package com.levi9.esport_web_app.dto;

import java.util.UUID;

import com.levi9.esport_web_app.model.Player;

public class PlayerResponse {

	private String id;
	private String nickname;
	private int wins;
	private int losses;
	private int elo;
	private int hoursPlayed;
	private UUID teamId;
	private Integer ratingAdjustment;

	// Constructor to populate from a Player entity
	public PlayerResponse(Player player) {
		this.id = player.getId().toString();
		this.nickname = player.getNickname();
		this.wins = player.getWins();
		this.losses = player.getLosses();
		this.elo = player.getElo();
		this.hoursPlayed = player.getHoursPlayed();
		this.teamId = player.getTeam() != null ? player.getTeam().getId() : null;
		this.ratingAdjustment = player.getRatingAdjustment();
	}

	// Getters and Setters
	public String getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public int getElo() {
		return elo;
	}

	public int getHoursPlayed() {
		return hoursPlayed;
	}

	public UUID getTeamId() {
		return teamId;
	}

	public Integer getRatingAdjustment() {
		return ratingAdjustment;
	}
}