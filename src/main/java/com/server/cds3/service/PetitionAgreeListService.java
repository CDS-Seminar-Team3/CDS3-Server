package com.server.cds3.service;

import com.server.cds3.domain.Petition;
import com.server.cds3.domain.PetitionMember;
import com.server.cds3.dto.PetitionAgreeListResponseDto;
import com.server.cds3.repository.PetitionMemberRepository;
import com.server.cds3.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetitionAgreeListService {

    private final PetitionRepository petitionRepository;
    private final PetitionMemberRepository petitionMemberRepository;

    @Autowired
    public PetitionAgreeListService(PetitionRepository petitionRepository, PetitionMemberRepository petitionMemberRepository) {
        this.petitionRepository = petitionRepository;
        this.petitionMemberRepository = petitionMemberRepository;
    }

    @Transactional(readOnly = true)
    public PetitionAgreeListResponseDto getPetitionAgreeList(Long petitionId) {
        Petition petition = petitionRepository.findById(petitionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid petition ID"));

        long agreeNumber = petitionMemberRepository.countByPetition(petition);
        List<PetitionMember> petitionMembers = petitionMemberRepository.findByPetition(petition);
        List<PetitionAgreeListResponseDto.AgreeInfo> agreeList = new ArrayList<>();

        for (PetitionMember member : petitionMembers) {
            PetitionAgreeListResponseDto.AgreeInfo agreeInfo = new PetitionAgreeListResponseDto.AgreeInfo();
            agreeInfo.setUserName(member.getUser().getName());
            agreeInfo.setAgreeContent(member.getAgreeContent());
            agreeList.add(agreeInfo);
        }

        return new PetitionAgreeListResponseDto(agreeNumber, agreeList);
    }
}