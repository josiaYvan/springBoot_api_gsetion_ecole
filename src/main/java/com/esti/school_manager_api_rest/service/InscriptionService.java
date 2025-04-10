package com.esti.school_manager_api_rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esti.school_manager_api_rest.DTO.CoursDTO;
import com.esti.school_manager_api_rest.DTO.EtudiantDTO;
import com.esti.school_manager_api_rest.DTO.InscriptionRequestDTO;
import com.esti.school_manager_api_rest.model.Cours;
import com.esti.school_manager_api_rest.model.Etudiant;
import com.esti.school_manager_api_rest.model.Inscription;
import com.esti.school_manager_api_rest.repository.CoursRepository;
import com.esti.school_manager_api_rest.repository.EtudiantRepository;
import com.esti.school_manager_api_rest.repository.InscriptionRepository;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private CoursRepository coursRepository;

    public List<InscriptionRequestDTO> getAll() {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        
        return inscriptions.stream()
                .map(inscription -> {
                    Etudiant etudiant = inscription.getEtudiant();
                    EtudiantDTO etudiantDTO = new EtudiantDTO();
                    etudiantDTO.setId(etudiant.getId());
                    etudiantDTO.setNom(etudiant.getNom());
                    etudiantDTO.setEmail(etudiant.getEmail());
                    
                    Cours cours = inscription.getCours();
                    CoursDTO coursDTO = new CoursDTO();
                    coursDTO.setId(cours.getId());
                    coursDTO.setNom(cours.getTitre());
                    coursDTO.setDescription(cours.getDescription());

                    InscriptionRequestDTO inscriptionDTO = new InscriptionRequestDTO();
                    inscriptionDTO.setEtudiant(etudiantDTO); 
                    inscriptionDTO.setCours(coursDTO);
                    inscriptionDTO.setDateInscription(inscription.getDateInscription());
                    inscriptionDTO.setId(inscription.getId());
                    inscriptionDTO.setCoursId(inscription.getCours().getId());
                    inscriptionDTO.setEtudiantId(inscription.getEtudiant().getId());
                    return inscriptionDTO;
                })
                .collect(Collectors.toList());
    }


    public Inscription getById(Long id) {
        return inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée"));
    }

    public InscriptionRequestDTO getByIdDTO(Long id) {
        Inscription inscription = inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée"));

        // Peupler EtudiantDTO
        Etudiant etudiant = inscription.getEtudiant();
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.setId(etudiant.getId());
        etudiantDTO.setNom(etudiant.getNom());
        etudiantDTO.setEmail(etudiant.getEmail());

        // Peupler CoursDTO
        Cours cours = inscription.getCours();
        CoursDTO coursDTO = new CoursDTO();
        coursDTO.setId(cours.getId());
        coursDTO.setNom(cours.getTitre());
        coursDTO.setDescription(cours.getDescription());

        // Retourner l'Inscription avec les objets EtudiantDTO et CoursDTO
        InscriptionRequestDTO inscriptionDTO = new InscriptionRequestDTO();
        inscriptionDTO.setEtudiant(etudiantDTO);
        inscriptionDTO.setCours(coursDTO);
        inscriptionDTO.setDateInscription(inscription.getDateInscription());
        inscriptionDTO.setId(inscription.getId());
        inscriptionDTO.setCoursId(inscription.getCours().getId());
        inscriptionDTO.setEtudiantId(inscription.getEtudiant().getId());

        return inscriptionDTO;
    }

    public Inscription create(InscriptionRequestDTO inscriptionDTO) {
        Etudiant etudiant = etudiantRepository.findById(inscriptionDTO.getEtudiantId())
                .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));
        Cours cours = coursRepository.findById(inscriptionDTO.getCoursId())
                .orElseThrow(() -> new RuntimeException("Cours non trouvé"));

        Inscription inscription = new Inscription();
        inscription.setEtudiant(etudiant);
        inscription.setCours(cours);
        inscription.setDateInscription(inscriptionDTO.getDateInscription());

        return inscriptionRepository.save(inscription);
    }

    public Inscription update(Long id, InscriptionRequestDTO inscriptionDTO) {
        return inscriptionRepository.findById(id).map(inscription -> {
            Etudiant etudiant = etudiantRepository.findById(inscriptionDTO.getEtudiantId())
                    .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));
            Cours cours = coursRepository.findById(inscriptionDTO.getCoursId())
                    .orElseThrow(() -> new RuntimeException("Cours non trouvé"));

            inscription.setEtudiant(etudiant);
            inscription.setCours(cours);
            inscription.setDateInscription(inscriptionDTO.getDateInscription());
            return inscriptionRepository.save(inscription);
        }).orElseThrow(() -> new RuntimeException("Inscription non trouvée"));
    }

    public void delete(Long id) {
        inscriptionRepository.deleteById(id);
    }
    
    public List<EtudiantDTO> getEtudiantsByCours(Long coursId) {
        List<Inscription> inscriptions = inscriptionRepository.findByCoursId(coursId);
        
        return inscriptions.stream()
                .map(inscription -> {
                    Etudiant etudiant = inscription.getEtudiant();
                    EtudiantDTO etudiantDTO = new EtudiantDTO();
                    etudiantDTO.setId(etudiant.getId());
                    etudiantDTO.setNom(etudiant.getNom());
                    etudiantDTO.setEmail(etudiant.getEmail());
                    return etudiantDTO;
                })
                .collect(Collectors.toList());
    }
    
    public List<CoursDTO> getCoursByEtudiant(Long etudiantId) {
        List<Inscription> inscriptions = inscriptionRepository.findByEtudiantId(etudiantId);

        return inscriptions.stream()
                .map(inscription -> {
                    Cours cours = inscription.getCours();
                    CoursDTO coursDTO = new CoursDTO();
                    coursDTO.setId(cours.getId());
                    coursDTO.setNom(cours.getTitre());
                    coursDTO.setDescription(cours.getDescription());
                    return coursDTO;
                })
                .collect(Collectors.toList());
    }


}
