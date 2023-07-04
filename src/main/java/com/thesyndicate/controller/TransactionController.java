package com.thesyndicate.controller;

import com.thesyndicate.entity.Transaction;
import com.thesyndicate.entity.Wallet;
import com.thesyndicate.repository.TransactionRepository;
import jakarta.el.ELManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TransactionController {
    @Autowired
    TransactionRepository repository;

    public List<Transaction> findAll(){
        try{
            return repository.findAll();
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public void save(Transaction transaction){
        repository.save(transaction);
    }

    public List<Transaction> findByWallet(Wallet wallet){
        try{
            var set = new LinkedHashSet<Transaction>();
            set.addAll(repository.findByIdOriginWallet(wallet));
            set.addAll(repository.findByIdDestinationWallet(wallet));
            var list = new LinkedList<Transaction>(set);

            return list;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
