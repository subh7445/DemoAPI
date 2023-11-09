package com.demo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.api.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
