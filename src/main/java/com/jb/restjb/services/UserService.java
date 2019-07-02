package com.jb.restjb.services;

import com.jb.restjb.entities.Company;
import com.jb.restjb.entities.Post;
import com.jb.restjb.entities.User;
import com.jb.restjb.repositories.CompanyRepository;
import com.jb.restjb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<User> get() {
		List<User> result = new ArrayList<>();
		userRepository.findAll().forEach(result::add);
		return result;
	}

	public Optional<User> get(Long id) {
		return userRepository.findById(id);
	}
	public User getByName(String userName) {
		return userRepository.findByUserName(userName);
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	public List<Post> feed(String userName) {
		return userRepository.feed(userName);
	}

}
