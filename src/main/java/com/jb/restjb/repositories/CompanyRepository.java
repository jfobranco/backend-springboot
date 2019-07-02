package com.jb.restjb.repositories;

import com.jb.restjb.entities.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

}
