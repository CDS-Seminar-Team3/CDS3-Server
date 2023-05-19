package com.server.cds3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetitionAgreeListResponseDto {
    private long agreeNumber;
    private List<AgreeInfo> agreeList;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AgreeInfo {
        private String userName;
        private String agreeContent;
    }
}