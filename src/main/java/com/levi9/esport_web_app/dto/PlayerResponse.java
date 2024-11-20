package com.levi9.esport_web_app.dto;

import java.util.UUID;

import com.levi9.esport_web_app.model.Player;

import lombok.Data;

@Data
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
}