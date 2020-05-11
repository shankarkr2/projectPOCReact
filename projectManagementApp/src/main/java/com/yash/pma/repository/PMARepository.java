package com.yash.pma.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yash.pma.domain.Project;

@Repository
public interface PMARepository extends CrudRepository<Project, Long> {

    Project getById(Long id);
}
