package com.server.cds3.service;

import com.server.cds3.domain.Petition;
import com.server.cds3.domain.PetitionMember;
import com.server.cds3.domain.User;
import com.server.cds3.dto.response.myPetition.GetMyPetitionResponseDto;
import com.server.cds3.repository.PetitionMemberRepository;
import com.server.cds3.repository.PetitionRepository;
import com.server.cds3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPetitionService {
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final PetitionMemberRepository petitionMemberRepository;

    public List<GetMyPetitionResponseDto> getMyPetition(long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        List<Petition> petitionList = petitionRepository.findAllByUser(user);
        List<GetMyPetitionResponseDto> myPetitionList = new ArrayList<>();

        petitionList.forEach(petition -> {
            List<PetitionMember> petitionMemberList = petitionMemberRepository.findAllByPetition(petition);

            GetMyPetitionResponseDto getMyPetitionResponseDto = GetMyPetitionResponseDto.builder()
                    .petitionId(petition.getId())
                    .category(petition.getCategory())
                    .title(petition.getTitle())
                    .agreeNumber(petitionMemberList.size())
                    .build();

            myPetitionList.add(getMyPetitionResponseDto);
        });

        return myPetitionList;
    }
}
