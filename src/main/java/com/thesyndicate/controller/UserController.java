package com.thesyndicate.controller;

import com.thesyndicate.entity.User;
import com.thesyndicate.entity.UserKt;

import com.thesyndicate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {
	private final UserRepository repository;

	@Autowired
	public UserController(UserRepository userRepository){
		this.repository = userRepository;
	}

	/**
	 *
	 * @return all users in the database
	 */
	public List<User> getAll() {
		return repository.findAll();
	}

	/**
	 * verifies login data
	 * @param username username
	 * @param password password
	 * @return User object in case of success and otherwise
	 */
	public User authenticate(String username, String password) {
		try{
			User user = repository.findByUsername(username);
			if (Objects.isNull(user))
				return null;
			else if (UserKt.comparePassword(password, user.getPassword()))
				return user;
			return null;
		}
		catch (EmptyResultDataAccessException e){
			return null;
		}
	}

	/**
	 * virifies if a username exists in the database
	 * @param username the username to search in the database
	 * @return true if found and false otherwise
	 */
	public boolean exists(String username){
		try{
			return repository.findByUsername(username) != null;
		}
		catch(EmptyResultDataAccessException e){
			return false; //object does not exist
		}
	}

	public void save(User newUser){
		repository.save(newUser);
	}
}
