package com.server.cds3.repository;

import com.server.cds3.domain.PetitionMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionMemberRepository extends JpaRepository<PetitionMember, Long> {

}
