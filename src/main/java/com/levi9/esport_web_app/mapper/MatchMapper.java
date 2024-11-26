package com.levi9.esport_web_app.mapper;

import com.levi9.esport_web_app.dto.MatchRequest;
import com.levi9.esport_web_app.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    @Mapping(target = "id", source = "matchRequest")
    @Mapping(target = "winningTeamId", source = "matchRequest.winningTeamId")
    @Mapping(target = "duration", source = "matchRequest.duration")
    Match matchRequestToMatch(MatchRequest matchRequest);
}