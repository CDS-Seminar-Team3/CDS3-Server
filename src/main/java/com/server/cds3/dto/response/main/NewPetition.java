package com.server.cds3.dto.response.main;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewPetition {
    private long petitionId;
    private String category;
    private String title;
    private int agreeNumber;
    private boolean isSee;
    private boolean isAgree;

    @Builder
    public NewPetition(long petitionId, String category, String title, int agreeNumber, boolean isSee, boolean isAgree) {
        this.petitionId = petitionId;
        this.category = category;
        this.title = title;
        this.agreeNumber = agreeNumber;
        this.isSee = isSee;
        this.isAgree = isAgree;
    }
}
