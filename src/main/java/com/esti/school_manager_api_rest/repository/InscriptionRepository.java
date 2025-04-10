package com.esti.school_manager_api_rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esti.school_manager_api_rest.model.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> findByCoursId(Long coursId); 
    List<Inscription> findByEtudiantId(Long etudiantId);

}
