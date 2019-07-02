package com.jb.restjb.controllers;

import com.jb.restjb.entities.Company;
import com.jb.restjb.entities.Post;
import com.jb.restjb.entities.Service;
import com.jb.restjb.entities.User;
import com.jb.restjb.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	CompanyService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<Company> getList() {
		return service.get();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	Company create(@RequestBody Company comp) {
		return service.create(comp);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<Company> get(@PathVariable Long id) {
		Optional<Company> result = service.get(id);
		if (result.isPresent()) {
			return ResponseEntity.ok().body(result.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Patch / Put ?

	@RequestMapping(value = "/{id}/users/", method = RequestMethod.GET)
	ResponseEntity<List<User>> followers(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.followers(id));
	}

	@RequestMapping(value = "/{id}/users/{userId}", method = RequestMethod.GET)
	ResponseEntity<Boolean> follows(Principal principal, @PathVariable Long id, @PathVariable String userId) {
		if (!userId.equals("me") || principal == null) {
			// Not supported by now to call this endpoint with other user than logged user
			return ResponseEntity.badRequest().build();
		} else {
			userId = principal.getName();
		}

		return ResponseEntity.ok().body(service.following(userId,  id));
	}

	@RequestMapping(value = "/{id}/users/{userId}", method = RequestMethod.POST)
	ResponseEntity<Void> follow(Principal principal, @PathVariable Long id, @PathVariable String userId) {
		if (!userId.equals("me") || principal == null) {
			// Not supported by now to call this endpoint with other user than logged user
			return ResponseEntity.badRequest().build();
		} else {
			userId = principal.getName();
		}

		boolean result = service.follow(userId, id);
		if (result)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.badRequest().build();
	}

	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	List<Post> getPosts(@PathVariable Long id) {
		return service.getPosts(id);
	}

	@RequestMapping(value = "/{id}/posts", method = RequestMethod.POST)
	Post createPost(@PathVariable Long id, @RequestBody Post post) {
		return service.createPost(id, post);
	}

	@RequestMapping(value = "/{id}/services", method = RequestMethod.GET)
	List<Service> getServices(@PathVariable Long id) {
		return service.getServices(id);
	}

	@RequestMapping(value = "/{id}/services", method = RequestMethod.POST)
	Service createService(@PathVariable Long id, @RequestBody Service svc) {
		return service.createService(id, svc);
	}
}
