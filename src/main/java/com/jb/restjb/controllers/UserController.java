package com.jb.restjb.controllers;

import com.jb.restjb.entities.Company;
import com.jb.restjb.entities.Post;
import com.jb.restjb.entities.User;
import com.jb.restjb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	List<User> getList() {
		return service.get();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	User create(@RequestBody User user) {
		return service.create(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<User> get(@PathVariable Long id) {
		Optional<User> result = service.get(id);
		if (result.isPresent()) {
			return ResponseEntity.ok().body(result.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	ResponseEntity<List<Post>> userFeed(Principal principal, @PathVariable String id) {
		if (!id.equals("me") || principal == null) {
			// Not supported by now to call this endpoint with other user than logged user
			return ResponseEntity.badRequest().build();
		} else {
			id = principal.getName();
		}

		// Pagination
		return ResponseEntity.ok().body(service.feed(id));
	}

	@RequestMapping(value = "/{id}/companies", method = RequestMethod.GET)
	ResponseEntity<List<Company>> userCompanies(Principal principal, @PathVariable String id) {
		if (!id.equals("me") || principal == null) {
			// Not supported by now to call this endpoint with other user than logged user
			return ResponseEntity.badRequest().build();
		} else {
			id = principal.getName();
		}

		return ResponseEntity.ok().body(service.getByName(id).getFollowing());
	}
}
