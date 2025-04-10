package com.esti.school_manager_api_rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esti.school_manager_api_rest.DTO.CoursRequestDTO;
import com.esti.school_manager_api_rest.model.Cours;
import com.esti.school_manager_api_rest.service.CoursService;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @GetMapping
    public List<Cours> getAll() {
        return coursService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
        	Cours cours = coursService.getById(id);
            return ResponseEntity.ok(cours); 
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Cours non trouvé avec ID : " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Cours> create(@RequestBody CoursRequestDTO dto) {
        return ResponseEntity.ok(coursService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cours> update(@PathVariable Long id, @RequestBody CoursRequestDTO dto) {
        return ResponseEntity.ok(coursService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coursService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
