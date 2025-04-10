package com.esti.school_manager_api_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esti.school_manager_api_rest.DTO.CoursDTO;
import com.esti.school_manager_api_rest.DTO.EtudiantDTO;
import com.esti.school_manager_api_rest.DTO.InscriptionRequestDTO;
import com.esti.school_manager_api_rest.model.Inscription;
import com.esti.school_manager_api_rest.service.InscriptionService;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @GetMapping
    public List<InscriptionRequestDTO> getAll() {
        return inscriptionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            InscriptionRequestDTO inscriptionDTO = inscriptionService.getByIdDTO(id);
            return ResponseEntity.ok(inscriptionDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inscription non trouvée avec ID : " + id);
        }
    }

    @PostMapping
    public Inscription create(@RequestBody InscriptionRequestDTO inscriptionDTO) {
        return inscriptionService.create(inscriptionDTO);
    }

    @PutMapping("/{id}")
    public Inscription update(@PathVariable Long id, @RequestBody InscriptionRequestDTO inscriptionDTO) {
        return inscriptionService.update(id, inscriptionDTO);
    }

    @GetMapping("/cours/{coursId}/etudiants")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsByCours(@PathVariable Long coursId) {
        List<EtudiantDTO> etudiants = inscriptionService.getEtudiantsByCours(coursId);
        return ResponseEntity.ok(etudiants);
    }
    
    @GetMapping("/etudiants/{etudiantId}/cours")
    public ResponseEntity<List<CoursDTO>> getCoursByEtudiant(@PathVariable Long etudiantId) {
        List<CoursDTO> coursList = inscriptionService.getCoursByEtudiant(etudiantId);
        return ResponseEntity.ok(coursList);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inscriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
