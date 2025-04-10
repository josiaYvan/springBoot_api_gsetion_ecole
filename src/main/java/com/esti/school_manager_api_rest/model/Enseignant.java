package com.esti.school_manager_api_rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Enseignant {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    private String email;

    // Evite les boucles infinies (si tu fais une relation inverse)
    @JsonIgnoreProperties("enseignant")
    @OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL)
    private List<Cours> cours;
    
    public List<Cours> getCours() {
		return cours;
	}
	public void setCours(List<Cours> cours) {
		this.cours = cours;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
