package com.thesyndicate.controller;

import com.thesyndicate.entity.User;
import com.thesyndicate.entity.UserKt;

import com.thesyndicate.util.CaptchaManagerKt;

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
	public List<User> findAll() {
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
}
