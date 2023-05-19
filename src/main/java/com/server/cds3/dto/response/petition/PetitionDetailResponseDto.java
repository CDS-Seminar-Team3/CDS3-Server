package com.server.cds3.dto.response.petition;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Setter
@Data
public class PetitionDetailResponseDto {
    private boolean expiration;
    private String category;
    private Long petitionId;
    private String title;
    private String name;
    private String nickname;
    private LocalDateTime createdAt;
    private Long agreeNumber;
    private String content;
}
