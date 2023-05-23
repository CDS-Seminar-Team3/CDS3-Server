package com.server.cds3.controller;

import com.server.cds3.common.dto.ApiResponseDto;
import com.server.cds3.common.exception.SuccessStatus;
import com.server.cds3.dto.response.myPetition.GetMyPetitionResponseDto;
import com.server.cds3.service.MyPetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypetition")
public class MyPetitionController {

    private final MyPetitionService myPetitionService;

    @GetMapping("")
    public ApiResponseDto<List<GetMyPetitionResponseDto>> getMyPetition(
            @RequestHeader("user-id") long userId
    ){
        return ApiResponseDto.success(SuccessStatus.GET_MY_PETITION, myPetitionService.getMyPetition(userId));
    }
}
