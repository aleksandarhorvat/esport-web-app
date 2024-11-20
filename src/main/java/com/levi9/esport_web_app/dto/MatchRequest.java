package com.levi9.esport_web_app.dto;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MatchRequest {
	@NotNull
	private UUID team1Id;

	@NotNull
	private UUID team2Id;

	private UUID winningTeamId; // Nullable for draws

	@Min(1)
	private int duration;

	public UUID getTeam1Id() {
		return team1Id;
	}

	public void setTeam1Id(UUID team1Id) {
		this.team1Id = team1Id;
	}

	public UUID getTeam2Id() {
		return team2Id;
	}

	public void setTeam2Id(UUID team2Id) {
		this.team2Id = team2Id;
	}

	public UUID getWinningTeamId() {
		return winningTeamId;
	}

	public void setWinningTeamId(UUID winningTeamId) {
		this.winningTeamId = winningTeamId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
