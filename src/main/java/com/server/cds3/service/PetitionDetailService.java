package com.server.cds3.service;

import com.server.cds3.domain.Petition;
import com.server.cds3.domain.PetitionMember;
import com.server.cds3.domain.User;
import com.server.cds3.dto.request.petition.PostPetitionAgreeRequestDto;
import com.server.cds3.dto.response.petition.PetitionDetailResponseDto;
import com.server.cds3.dto.response.petition.PostPetitionAgreeResponseDto;
import com.server.cds3.repository.PetitionMemberRepository;
import com.server.cds3.repository.PetitionRepository;
import com.server.cds3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class PetitionDetailService {
    private final PetitionMemberRepository petitionMemberRepository;
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;

    public PostPetitionAgreeResponseDto postPetitionAgree(PostPetitionAgreeRequestDto requestDto, long petitionId, long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 청원입니다."));

        PetitionMember petitionMember = petitionMemberRepository.save(PetitionMember.builder()
                .agreeContent(requestDto.getAgreeContent())
                .user(user)
                .petition(petition)
                .build());

        return PostPetitionAgreeResponseDto.builder()
                .id(petition.getId())
                .build();
    }

    public PetitionDetailResponseDto getPetitionDetail(long petitionId) {
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new IllegalArgumentException("Petition not found with id: " + petitionId));

        PetitionDetailResponseDto responseDto = PetitionDetailResponseDto.builder()
                .category(petition.getCategory())
                .petitionId(petition.getId())
                .title(petition.getTitle())
                .name(petition.getUser().getName())
                .nickname(petition.getUser().getNickname())
                .createdAt(petition.getCreatedAt())
                .content(petition.getContent())
                .build();

        Long agreeNumber = petitionMemberRepository.countByPetitionId(petitionId);
        responseDto.setAgreeNumber(agreeNumber);

        LocalDateTime createdAt = petition.getCreatedAt();
        LocalDateTime expirationThreshold = createdAt.plus(1, ChronoUnit.MONTHS);
        boolean isExpired = LocalDateTime.now().isAfter(expirationThreshold);
        responseDto.setExpiration(isExpired);

        return responseDto;
    }

}
