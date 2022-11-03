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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
	
	@Id
	
	@NonNull
	private int id;
	private String nom;
	private String reference;
	private double prix;
	private double poids;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "produit_magasin",
		joinColumns = @JoinColumn (name= "idProduit"),
		inverseJoinColumns = @JoinColumn(name = "idMagasin"))
	private List<Magasin> listeMagasin;
}
