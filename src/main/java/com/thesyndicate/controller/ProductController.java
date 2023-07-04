package com.thesyndicate.controller;

import com.thesyndicate.entity.Product;
import com.thesyndicate.entity.User;
import com.thesyndicate.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository repository;

    public void save(Product product){
        repository.save(product);
    }

    public List<Product> findBySeller(User seller){
        try{
            return repository.findByIdSeller(seller);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Product> findAll(){
        try{
            return repository.findAll();
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Product findById(long id){
        try{
            return repository.findById(id).get();
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
