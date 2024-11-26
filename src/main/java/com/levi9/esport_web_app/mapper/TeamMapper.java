package com.levi9.esport_web_app.mapper;

import com.levi9.esport_web_app.dto.TeamRequest;
import com.levi9.esport_web_app.dto.TeamResponse;
import com.levi9.esport_web_app.model.Team;

import java.util.ArrayList;
import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = PlayerMapper.class)
public interface TeamMapper {

    // Map from TeamRequest to Team entity (ignoring players for now)
    @Mapping(target = "id", ignore = true) // ID is auto-generated
    @Mapping(target = "players", ignore = true) // Handle player mapping separately
    Team teamRequestToTeam(TeamRequest request);

    // Map from Team entity to TeamResponse DTO
    @Mapping(target = "players", source = "players") // Uses PlayerMapper implicitly
    TeamResponse teamToTeamResponse(Team team);
    
    // BeforeMapping to generate the UUID if not already set
    @BeforeMapping
    default void setIdIfNotPresent(@MappingTarget Team team) {
        if (team.getId() == null) {
            team.setId(UUID.randomUUID()); // Set ID if not already set
        }
    }

    // AfterMapping to set default values for players if needed
    @AfterMapping
    default void setDefaultValues(@MappingTarget Team team) {
        if (team.getPlayers() == null) {
            team.setPlayers(new ArrayList<>()); // Initialize an empty list if players are not set
        }
    }
}