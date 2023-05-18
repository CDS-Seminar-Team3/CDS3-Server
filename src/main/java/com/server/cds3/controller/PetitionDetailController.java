package com.server.cds3.controller;

import com.server.cds3.common.dto.ApiResponseDto;
import com.server.cds3.common.exception.SuccessStatus;
import com.server.cds3.dto.request.PostPetitionAgreeRequestDto;
import com.server.cds3.dto.request.PostPetitionAgreeResponseDto;
import com.server.cds3.service.PetitionDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PetitionDetailController {

    private final PetitionDetailService petitionDetailService;

    @PostMapping("/{petitionId}/detail/agree")
    public ApiResponseDto<PostPetitionAgreeResponseDto> postPetitionAgree(
            @RequestHeader("user-id") long userId,
            @RequestBody @Valid final PostPetitionAgreeRequestDto requestDto,
            @PathVariable long petitionId) {
        return ApiResponseDto.success(SuccessStatus.POST_AGREE_SUCCESS, petitionDetailService.postPetitionAgree(requestDto, petitionId, userId));

    }

}
