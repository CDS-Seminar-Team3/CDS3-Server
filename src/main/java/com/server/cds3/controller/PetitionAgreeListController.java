package com.server.cds3.controller;

import com.server.cds3.dto.PetitionAgreeListResponseDto;
import com.server.cds3.service.PetitionAgreeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/petitions")
public class PetitionAgreeListController {

    private final PetitionAgreeListService petitionAgreeListService;

    @Autowired
    public PetitionAgreeListController(PetitionAgreeListService petitionAgreeListService) {
        this.petitionAgreeListService = petitionAgreeListService;
    }

    @GetMapping("/{petitionId}/agree")
    public PetitionAgreeListResponseDto getPetitionAgreeList(@RequestHeader("petition-id") Long petitionId) {
        return petitionAgreeListService.getPetitionAgreeList(petitionId);
    }
}