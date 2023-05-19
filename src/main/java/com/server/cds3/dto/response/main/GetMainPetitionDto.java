package com.server.cds3.dto.response.main;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetMainPetitionDto {
    private IssuePetition issuePetition;
    private List<NewPetition> newPetitionList;
}
