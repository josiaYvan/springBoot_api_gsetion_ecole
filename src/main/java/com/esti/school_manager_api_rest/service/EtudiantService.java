package com.esti.school_manager_api_rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esti.school_manager_api_rest.model.Etudiant;
import com.esti.school_manager_api_rest.repository.EtudiantRepository;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAll() {
        return etudiantRepository.findAll();
    }

    public Etudiant getById(Long id) {
        return etudiantRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Etudiant non trouvé avec ID : " + id));
    }

    public Etudiant create(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant update(Long id, Etudiant details) {
        return etudiantRepository.findById(id).map(etudiant -> {
            etudiant.setNom(details.getNom());
            etudiant.setEmail(details.getEmail());
            return etudiantRepository.save(etudiant);
        }).orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
    }

    public void delete(Long id) {
        etudiantRepository.deleteById(id);
    }
}

