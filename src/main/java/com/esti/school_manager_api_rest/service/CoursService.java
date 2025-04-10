package com.esti.school_manager_api_rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esti.school_manager_api_rest.DTO.CoursRequestDTO;
import com.esti.school_manager_api_rest.model.Cours;
import com.esti.school_manager_api_rest.model.Enseignant;
import com.esti.school_manager_api_rest.repository.CoursRepository;
import com.esti.school_manager_api_rest.repository.EnseignantRepository;

@Service
public class CoursService {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    public List<Cours> getAll() {
        return coursRepository.findAll();
    }

    public Cours getById(Long id) {
        return coursRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cours non trouvé avec ID : " + id));
    }

    public Cours create(CoursRequestDTO dto) {
        Enseignant enseignant = enseignantRepository.findById(dto.getEnseignantId())
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable avec l'id: " + dto.getEnseignantId()));

        Cours cours = new Cours();
        cours.setTitre(dto.getTitre());
        cours.setDescription(dto.getDescription());
        cours.setEnseignant(enseignant);

        return coursRepository.save(cours);
    }

    public Cours update(Long id, CoursRequestDTO dto) {
        return coursRepository.findById(id).map(cours -> {
            cours.setTitre(dto.getTitre());
            cours.setDescription(dto.getDescription());

            Enseignant enseignant = enseignantRepository.findById(dto.getEnseignantId())
                    .orElseThrow(() -> new RuntimeException("Enseignant introuvable avec l'id: " + dto.getEnseignantId()));
            cours.setEnseignant(enseignant);

            return coursRepository.save(cours);
        }).orElseThrow(() -> new RuntimeException("Cours non trouvé"));
    }

    public void delete(Long id) {
        coursRepository.deleteById(id);
    }
}
