package com.thesyndicate.controller;

import com.thesyndicate.entity.User;
import com.thesyndicate.entity.Wallet;
import com.thesyndicate.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    @Autowired
    WalletRepository repository;

    public void save(Wallet wallet){
        repository.save(wallet);
    }

    public Wallet findByOwner(User owner){
        try{
            return repository.findByOwner(owner);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
