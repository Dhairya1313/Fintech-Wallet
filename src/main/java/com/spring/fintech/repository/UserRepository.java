package com.spring.fintech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.fintech.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
