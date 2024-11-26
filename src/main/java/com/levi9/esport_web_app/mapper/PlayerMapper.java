package com.levi9.esport_web_app.mapper;

import com.levi9.esport_web_app.dto.PlayerRequest;
import com.levi9.esport_web_app.dto.PlayerResponse;
import com.levi9.esport_web_app.model.Player;

import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    // Map from PlayerRequest to Player entity
	@Mapping(target = "id", ignore = true)
    @Mapping(target = "wins", ignore = true)
    @Mapping(target = "losses", ignore = true)
    @Mapping(target = "elo", ignore = true)
    @Mapping(target = "hoursPlayed", ignore = true)
    @Mapping(target = "ratingAdjustment", ignore = true)
    @Mapping(target = "team", ignore = true)
    Player playerRequestToPlayer(PlayerRequest request);
	
    @AfterMapping
    default void setDefaultValues(@MappingTarget Player player) {
        if (player.getId() == null) {
            player.setId(UUID.randomUUID()); // Generate a new UUID if not already set
        }
        if (player.getWins() == 0) {
            player.setWins(0); // Default wins
        }
        if (player.getLosses() == 0) {
            player.setLosses(0); // Default losses
        }
        if (player.getElo() == 0) {
            player.setElo(0); // Default elo
        }
        if (player.getHoursPlayed() == 0) {
            player.setHoursPlayed(0); // Default hours played
        }
        if (player.getRatingAdjustment() == 0) {
            player.setRatingAdjustment(0); // Default rating adjustment
        }
        if (player.getTeam() == null) {
            player.setTeam(null); // Default to null if no team is set
        }
    }
	
    // Map from Player entity to PlayerResponse DTO
    @Mapping(target = "teamId", source = "team.id")
    PlayerResponse playerToPlayerResponse(Player player);
}