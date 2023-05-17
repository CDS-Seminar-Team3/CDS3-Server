package com.server.cds3.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AuditingTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Petition> petitions = new ArrayList<>();

    @Builder
    private User(String name, String nickname, List<Petition> petitions) {
        this.name = name;
        this.nickname = nickname;
        this.petitions = petitions;
    }

}
