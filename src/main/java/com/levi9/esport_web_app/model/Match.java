package com.levi9.esport_web_app.model;

import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Match {
	@EmbeddedId
	private MatchId id;

	private UUID winningTeamId;

	private int duration;

}