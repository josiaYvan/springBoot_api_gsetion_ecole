package com.esti.school_manager_api_rest.DTO;

public class CoursDTO {

    private Long id;
    private String nom;
    private String description;  // Par exemple, pour ajouter plus de détails

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
