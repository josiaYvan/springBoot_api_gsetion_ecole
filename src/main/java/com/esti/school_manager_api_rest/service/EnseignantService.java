package com.esti.school_manager_api_rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esti.school_manager_api_rest.model.Enseignant;
import com.esti.school_manager_api_rest.repository.EnseignantRepository;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    public List<Enseignant> getAll() {
        return enseignantRepository.findAll();
    }

    public Enseignant getById(Long id) {
        return enseignantRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Enseignant non trouvé avec ID : " + id));
    }

    public Enseignant create(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    public Enseignant update(Long id, Enseignant details) {
        return enseignantRepository.findById(id).map(enseignant -> {
            enseignant.setNom(details.getNom());
            enseignant.setEmail(details.getEmail());
            return enseignantRepository.save(enseignant);
        }).orElseThrow(() -> new RuntimeException("Enseignant non trouvé"));
    }

    public void delete(Long id) {
        enseignantRepository.deleteById(id);
    }
}
