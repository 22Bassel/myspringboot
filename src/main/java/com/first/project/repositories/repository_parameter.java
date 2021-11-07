package com.first.project.repositories;

import com.first.project.entities.parameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface repository_parameter extends JpaRepository<parameters, String> {
}
