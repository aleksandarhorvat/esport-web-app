package com.levi9.esport_web_app.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class PlayerResponse {

	private UUID id;
	private String nickname;
	private int wins;
	private int losses;
	private int elo;
	private int hoursPlayed;
	private UUID teamId;
	private Integer ratingAdjustment;
}