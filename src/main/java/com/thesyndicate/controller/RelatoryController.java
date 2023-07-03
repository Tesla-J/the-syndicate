package com.thesyndicate.controller;

import com.thesyndicate.entity.Relatories;
import com.thesyndicate.repository.RelatoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelatoryController {

    @Autowired
    RelatoryRepository repository;

    public void save(Relatories relatory){
        repository.save(relatory);
    }
}
