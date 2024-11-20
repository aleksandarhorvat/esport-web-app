package com.levi9.esport_web_app.model;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
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

}