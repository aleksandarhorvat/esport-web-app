package com.levi9.esport_web_app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.levi9.esport_web_app.model.Player;

public interface PlayerRepository extends JpaRepository<Player, UUID> {

	boolean existsByNickname(String nickname);

}