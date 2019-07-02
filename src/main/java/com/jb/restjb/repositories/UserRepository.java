package com.jb.restjb.repositories;

import com.jb.restjb.entities.Post;
import com.jb.restjb.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	User findByUserName(@Param("userName") String userName);

	@Query("SELECT p FROM User u JOIN u.following c JOIN c.posts p WHERE u.userName = ?1 ORDER BY p.stamp desc")
	List<Post> feed(@Param("userName") String userName);
}