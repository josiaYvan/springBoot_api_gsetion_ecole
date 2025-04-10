package com.esti.school_manager_api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esti.school_manager_api_rest.model.Cours;

public interface CoursRepository extends JpaRepository<Cours, Long> {}
