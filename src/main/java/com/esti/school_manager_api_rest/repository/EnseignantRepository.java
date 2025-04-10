package com.esti.school_manager_api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esti.school_manager_api_rest.model.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {}
