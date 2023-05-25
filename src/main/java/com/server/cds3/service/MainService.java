package com.server.cds3.service;

import com.server.cds3.domain.Petition;
import com.server.cds3.domain.PetitionMember;
import com.server.cds3.domain.User;
import com.server.cds3.dto.response.main.GetMainPetitionDto;
import com.server.cds3.dto.response.main.IssuePetition;
import com.server.cds3.dto.response.main.NewPetition;
import com.server.cds3.repository.PetitionMemberRepository;
import com.server.cds3.repository.PetitionRepository;
import com.server.cds3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainService {
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final PetitionMemberRepository petitionMemberRepository;

    public GetMainPetitionDto getMainPetition(long userId) {
        // 가장 동의 많은거가 이슈 청원
        List<Petition> petitionList = petitionRepository.findAll();
        User users = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        int[] array = {0, 0};

        petitionList.forEach(petition -> {
            List<PetitionMember> allByPetition = petitionMemberRepository.findAllByPetition(petition);
            if (array[0] < allByPetition.size()) {
                array[0] = allByPetition.size();
                array[1] = petition.getId().intValue();
            }
        });
        Petition maxPetition = petitionRepository.findById((long) array[1])
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 청원입니다."));
        ;


        IssuePetition issuePetition = IssuePetition.builder()
                .petitionId(maxPetition.getId())
                .category(maxPetition.getCategory())
                .title(maxPetition.getTitle())
                .nickname(users.getNickname())
                .endDate(dateFormat(maxPetition.getCreatedAt()))
                .agreeNumber(array[0])
                .build();

        // 최근 청원 갖고오기
        List<NewPetition> newPetitionList = new ArrayList<>(); //최근청원 담을 리스트

        List<Petition> allPetition = petitionRepository.findAll();
        // 동의한 경우는 isAgree로 반환
        allPetition.forEach(petition -> {
            List<PetitionMember> allByPetition = petitionMemberRepository.findAllByPetition(petition); // 청원동의개수
            boolean isAgreed = false;
            for (PetitionMember petitionMember : allByPetition) {
                if (petitionMember.getUser().getId() == userId) {
                    isAgreed = true;
                    break;
                }
            }

            boolean isSee = false;
            if(petition.getId()%4 ==0){
                isSee = true;
            }

            newPetitionList.add(NewPetition.builder()
                    .isAgree(isAgreed)
                    .petitionId(petition.getId())
                    .category(petition.getCategory())
                    .title(petition.getTitle())
                    .agreeNumber(allByPetition.size())
                    .isSee(isSee)
                    .build());
        });


        return GetMainPetitionDto.builder()
                .issuePetition(issuePetition)
                .newPetitionList(newPetitionList)
                .build();

    }
    private String dateFormat(LocalDateTime localDateTime){
        LocalDateTime endDate  = localDateTime.plusMonths(1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return endDate.format(dateTimeFormatter).toString();
    }

}
