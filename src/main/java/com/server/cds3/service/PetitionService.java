package com.server.cds3.service;
import com.server.cds3.domain.Petition;
import com.server.cds3.domain.PetitionLink;
import com.server.cds3.domain.User;
import com.server.cds3.dto.request.petition.PetitionRegisterRequestDto;
import com.server.cds3.dto.response.petition.PetitionRegisterResponseDto;
import com.server.cds3.repository.PetitionLinkRepository;
import com.server.cds3.repository.PetitionRepository;
import com.server.cds3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PetitionService {
    private final UserRepository userRepository;
    private final PetitionRepository petitionRepository;
    private final PetitionLinkRepository petitionLinkRepository;


    public PetitionRegisterResponseDto registerPetition(PetitionRegisterRequestDto request, long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Petition petition = petitionRepository.save(Petition.builder()
                .category(request.getCategory())
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build());

        List<PetitionLink> petitionLinks = new ArrayList<>();
        if (request.getUrls() != null) {
            for (String url : request.getUrls()) {
                PetitionLink petitionLink = PetitionLink.builder()
                        .petition(petition)
                        .url(url)
                        .build();
                petitionLinkRepository.save(petitionLink);
                petitionLinks.add(petitionLink);
            }
        }

        return PetitionRegisterResponseDto.builder()
                .id(petition.getId())
                .build();
    }
}
