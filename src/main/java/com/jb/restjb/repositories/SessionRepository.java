package com.jb.restjb.repositories;

import com.jb.restjb.entities.Session;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends PagingAndSortingRepository<Session, Long> {

}
