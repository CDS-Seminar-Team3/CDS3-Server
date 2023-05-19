package com.server.cds3.controller;

import com.server.cds3.common.dto.ApiResponseDto;
import com.server.cds3.common.exception.SuccessStatus;
import com.server.cds3.dto.response.main.GetMainPetitionDto;
import com.server.cds3.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/main")
    public ApiResponseDto<GetMainPetitionDto> getMainPetition(
            @RequestHeader("user-id") long userId
    ) {
        return ApiResponseDto.success(SuccessStatus.SIGNUP_SUCCESS, mainService.getMainPetition(userId));
    }
}
