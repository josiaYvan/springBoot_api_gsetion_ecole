package com.esti.school_manager_api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esti.school_manager_api_rest.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {}
