package com.inti.model;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	
}
