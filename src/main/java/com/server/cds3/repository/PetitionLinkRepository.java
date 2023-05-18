package com.server.cds3.repository;

import com.server.cds3.domain.Petition;
import com.server.cds3.domain.PetitionLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionLinkRepository extends JpaRepository<PetitionLink, Long> {
}
