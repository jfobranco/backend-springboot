package com.jb.restjb.dao;

import com.jb.restjb.entities.Post;
import com.jb.restjb.entities.QCompany;
import com.jb.restjb.entities.QPost;
import com.jb.restjb.entities.QUser;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserDAO {
    @Autowired
    EntityManager em;

    public List<Post> feed(String userName) {
        JPAQuery<Post> query = new JPAQuery<>(em);
        QPost post = QPost.post;
        QUser user = QUser.user;
        QCompany company = QCompany.company;

        return query.select(post).from(user)
                .join(user.following, company)
                .join(company.posts, post)
                .where(user.userName.eq(userName))
                .orderBy(post.stamp.desc())
                .fetch();

    }
}
