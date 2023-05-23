package com.server.cds3.dto.response.main;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssuePetition {
    private long petitionId;
    private String category;
    private String title;
    private String nickname;
    private String endDate;
    private int agreeNumber;
}
