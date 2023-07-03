package com.thesyndicate.controller;

import com.thesyndicate.entity.Employee;
import com.thesyndicate.entity.User;
import com.thesyndicate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    /**
     * checks if an employee exists by searching for the name, bi or phone number
     * @param attribute the name, bi or the phone number
     * @return true if found, false otherwise
     */
    public boolean exists(String attribute){
        try{
            if(repository.findByName(attribute) != null
                    || repository.findByBi(attribute) != null
                    || repository.findByPhoneNumber(attribute) != null)
                return true;
            return false;
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    public void save(Employee e){
        repository.save(e);
    }

    public Employee findByUserId(User userId){
        try{
            return repository.findByUserId(userId);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
