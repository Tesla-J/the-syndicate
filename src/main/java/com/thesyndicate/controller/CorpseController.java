package com.thesyndicate.controller;

import com.thesyndicate.entity.Corpses;
import com.thesyndicate.repository.CorpseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorpseController {
    @Autowired
    CorpseRepository repository;

    public void save(Corpses corpse){
        repository.save(corpse);
    }
}
