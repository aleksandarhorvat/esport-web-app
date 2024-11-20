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
	public UUID id;

	public String nickname;

	public int wins;

	public int losses;

	public int elo;

	public int hoursPlayed;

	@ManyToOne
	@JoinColumn(name = "team_id")
	public Team team;

	public int ratingAdjustment;

}