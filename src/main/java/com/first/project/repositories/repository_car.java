package com.first.project.repositories;

import com.first.project.entities.car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface repository_car extends JpaRepository<car,Integer> {

}
