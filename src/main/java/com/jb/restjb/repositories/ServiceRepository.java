package com.jb.restjb.repositories;

import com.jb.restjb.entities.Service;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends PagingAndSortingRepository<Service, Long> {

}
