package com.server.cds3.repository;

import com.server.cds3.domain.Petition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PetitionRepositoryCustom {
    Page<Petition> findPetition(Pageable pageable, Long userId);
}
