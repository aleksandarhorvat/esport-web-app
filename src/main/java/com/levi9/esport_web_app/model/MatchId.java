package com.levi9.esport_web_app.model;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MatchId {
	@Column(insertable = false, updatable = false)
	private UUID team1Id;

	@Column(insertable = false, updatable = false)
	private UUID team2Id;

	// Override equals and hashCode
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MatchId matchId = (MatchId) o;
		return Objects.equals(team1Id, matchId.team1Id) && Objects.equals(team2Id, matchId.team2Id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(team1Id, team2Id);
	}

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

}
