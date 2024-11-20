package com.levi9.esport_web_app.model;

import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Match {
	@EmbeddedId
	private MatchId id;

	public UUID winningTeamId;

	public int duration;

	public MatchId getId() {
		return id;
	}

	public void setId(MatchId id) {
		this.id = id;
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