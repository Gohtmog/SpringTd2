package com.inti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "Produit_Spring")
@Data @NoArgsConstructor @AllArgsConstructor
public class Magasin {
	
	@Id
	@NonNull
	private int id;
	private String nom;
	private String adresse;
	private int cp;
	private String ville;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "produit_magasin",
		joinColumns = @JoinColumn(name = "idMagasin"),
		inverseJoinColumns = @JoinColumn (name= "idProduit"))
	private List<Produit> listeProduit;
 }
