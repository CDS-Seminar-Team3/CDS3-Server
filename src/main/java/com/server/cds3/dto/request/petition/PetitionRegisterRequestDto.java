package com.server.cds3.dto.request.petition;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Getter
@RequiredArgsConstructor
public class PetitionRegisterRequestDto {
    final private String category;
    final private String title;
    final private String content;
    final private List<String> urls;
}

