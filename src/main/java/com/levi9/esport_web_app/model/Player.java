package com.levi9.esport_web_app.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String nickname;

	private int wins;

	private int losses;

	private int elo;

	private int hoursPlayed;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	private int ratingAdjustment;

}