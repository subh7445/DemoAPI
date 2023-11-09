package com.demo.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.api.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData, Integer> {
    
}
