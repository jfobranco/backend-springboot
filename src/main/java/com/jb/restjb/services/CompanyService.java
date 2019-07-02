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
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<Company> get() {
		List<Company> result = new ArrayList<>();
		companyRepository.findAll().forEach(result::add);
		return result;
	}

	public Optional<Company> get(Long id) {
		return companyRepository.findById(id);
	}

	public Company create(Company comp) {
		return companyRepository.save(comp);
	}

	public List<User> followers(Long companyId) {
		Company company = companyRepository.findById(companyId).get();

		return company.getFollowers();
	}

	public boolean follow(String userName, Long companyId) {
		User user = userRepository.findByUserName(userName);
		Company company = companyRepository.findById(companyId).get();

		if (user.getFollowing().contains(company)) {
			user.getFollowing().remove(company);
			company.getFollowers().remove(user);
		} else {
			user.getFollowing().add(company);
			company.getFollowers().add(user);
		}

		userRepository.save(user);
		companyRepository.save(company);

		return true;
	}

	public boolean following(String userName, Long companyId) {
		// Maybe do this with a query
		User user = userRepository.findByUserName(userName);
		Company company = companyRepository.findById(companyId).get();

		return user.getFollowing().contains(company);
	}

	public List<Post> getPosts(Long id) {
		List<Post> result = new ArrayList<>();
		Optional<Company> comp = companyRepository.findById(id);
		if (comp.isPresent()) {
			result = comp.get().getPosts();
		}
		return result;
	}

	public Post createPost(Long id, Post post) {
		Optional<Company> comp = companyRepository.findById(id);
		comp.get().getPosts().add(post);

		return post;
	}

	public List<com.jb.restjb.entities.Service> getServices(Long id) {
		List<com.jb.restjb.entities.Service> result = new ArrayList<>();
		Optional<Company> comp = companyRepository.findById(id);
		if (comp.isPresent()) {
			result = comp.get().getServices();
		}
		return result;
	}

	public com.jb.restjb.entities.Service createService(Long id, com.jb.restjb.entities.Service service) {
		Optional<Company> comp = companyRepository.findById(id);
		comp.get().getServices().add(service);

		return service;
	}
}
