package com.first.project.repositories;

import com.first.project.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface repository_user  extends JpaRepository<user, Long> {

}
