package com.jb.restjb.dao;

import com.jb.restjb.RestJbApplication;
import com.jb.restjb.entities.Company;
import com.jb.restjb.entities.Post;
import com.jb.restjb.entities.User;
import com.jb.restjb.repositories.CompanyRepository;
import com.jb.restjb.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK,
        classes = RestJbApplication.class)
@RunWith(SpringRunner.class)
public class UserDAOTest {
    @Autowired
    CompanyRepository comps;

    @Autowired
    UserRepository users;

    @Autowired
    UserDAO dao;

    @Before
    public void setup() {

        Company comp = new Company();
        comp.setCompanyId(1L);
        comp.setName("Company");

        comps.save(comp);


        User user = new User();
        user.setUserId(1L);
        user.setUserName("name");
        List<Company> follows = new ArrayList<>();
        follows.add(comp);
        user.setFollowing(follows);

        Post post = new Post();
        post.setPostId(1L);
        post.setCompany(comp);
        post.setTitle("Test");

        List<Post> posts = new ArrayList<>();
        posts.add(post);

        comp.setPosts(posts);
        comps.save(comp);

        users.save(user);

    }

    @Test
    public void testFeed() {
        List<Post> result = dao.feed("name");
        assertEquals(1, result.size());
    }
}