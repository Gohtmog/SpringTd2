package com.inti.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "magasin_Spring")
@Data @NoArgsConstructor @AllArgsConstructor
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Magasin {
	
	@Id
	private int id;
	private String nom;
	private String adresse;
	private int cp;
	private String ville;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "produit_magasin",
		joinColumns = @JoinColumn(name = "idMagasin"),
		inverseJoinColumns = @JoinColumn (name= "idProduit"))
	//@JsonIgnore
	
	private List<Produit> listeProduit;
 }
