package com.server.cds3.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.cds3.domain.Petition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.server.cds3.domain.QPetition.petition;

@Repository
@RequiredArgsConstructor
public class PetitionRepositoryImpl implements PetitionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Petition> findPetition(Pageable pageable, Long userId) {
        List<Petition> petitions = queryFactory.selectFrom(petition)
                .from(petition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(petitions);
    }

}
