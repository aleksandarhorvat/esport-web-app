package com.levi9.esport_web_app.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchRequest {
	@NotNull
	private UUID team1Id;

	@NotNull
	private UUID team2Id;

	private UUID winningTeamId; // Nullable for draws

	@Min(1)
	private int duration;
}