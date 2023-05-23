package com.server.cds3.dto.response.myPetition;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMyPetitionResponseDto {
    private long petitionId;
    private String category;
    private String title;
    private int agreeNumber;
}
