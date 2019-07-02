package com.jb.restjb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jb.restjb.entities.Company;
import com.jb.restjb.entities.Post;
import com.jb.restjb.entities.Service;
import com.jb.restjb.entities.Session;
import com.jb.restjb.entities.User;
import com.jb.restjb.repositories.CompanyRepository;
import com.jb.restjb.repositories.ServiceRepository;
import com.jb.restjb.repositories.SessionRepository;
import com.jb.restjb.repositories.UserRepository;

@Component
public class DataLoader {
	
	private UserRepository userRepository;

	private CompanyRepository companyRepository;

	private ServiceRepository serviceRepository;

	private SessionRepository sessionRepository;

	@Autowired
    public DataLoader(UserRepository userRepository, CompanyRepository companyRepository, ServiceRepository serviceRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.serviceRepository = serviceRepository;
        this.sessionRepository = sessionRepository;
		loadUsers();
    }

    private void loadUsers() {
    	User user = new User();
    	user.setUserName("user");
        userRepository.save(user);
        
        Company comp = new Company();
        comp.setName("Comp 0");
        companyRepository.save(comp);
        
        for (int i=1; i<5; i++) {
	        Company comp1 = new Company();
	        comp1.setName("Comp " + i);
	        
	        Post post = new Post();
	        post.setCompany(comp1);
	        post.setTitle("Title " + i);
	        post.setContent("Post content " + i);
	        
	        List<Post> posts = new ArrayList<>();
	        posts.add(post);
	        comp1.setPosts(posts);
	
	        companyRepository.save(comp1);
        }
        
        Service service = new Service();
        service.setCode("123");
        service.setCompany(comp);
        service.setName("prod1");
        serviceRepository.save(service);
        
        Session sess = new Session();
        sess.setCompany(comp);
        sess.setUser(user);
        sessionRepository.save(sess);
    }
}
