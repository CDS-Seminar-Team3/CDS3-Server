package com.server.cds3.repository;

import com.server.cds3.domain.Petition;
import com.server.cds3.domain.PetitionMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionMemberRepository extends JpaRepository<PetitionMember, Long> {
    List<PetitionMember> findAllByPetition(Petition petition);
    Long countByPetitionId(Long petitionId);

    long countByPetition(Petition petition);

    List<PetitionMember> findByPetition(Petition petition);
}
