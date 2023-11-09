package com.demo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.api.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
