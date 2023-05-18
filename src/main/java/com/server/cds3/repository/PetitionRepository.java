package com.server.cds3.repository;

import com.server.cds3.domain.Petition;
import com.server.cds3.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetitionRepository extends JpaRepository<Petition, Long> {
    List<Petition> findAllByUser(User user);

}
