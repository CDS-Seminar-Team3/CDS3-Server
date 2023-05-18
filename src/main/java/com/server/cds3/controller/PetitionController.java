package com.server.cds3.controller;

import com.server.cds3.common.dto.ApiResponseDto;
import com.server.cds3.common.exception.SuccessStatus;
import com.server.cds3.dto.request.petition.PetitionRegisterRequestDto;
import com.server.cds3.dto.response.petition.PetitionRegisterResponseDto;
import com.server.cds3.service.PetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PetitionController {

    private final PetitionService petitionService;
    //* 청원글 생성하기
    @PostMapping("/petition")
    public ApiResponseDto<PetitionRegisterResponseDto> registerPetition(
            @RequestHeader("user-id") long userId,
            @RequestBody @Valid final PetitionRegisterRequestDto request
            ){
        List<String> urls = request.getUrls();
        return ApiResponseDto.success(SuccessStatus.POST_AGREE_SUCCESS, petitionService.registerPetition(request,userId));
    }

}
