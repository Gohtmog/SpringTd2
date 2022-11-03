package com.inti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Produit;
import com.inti.repository.IproduitRepository;

@RestController
@RequestMapping("produit")
public class ProduitController {
	@Autowired
	IproduitRepository ipr;

	@PostMapping("/affichageProduit")
	public String affichageNomProduit(
			@RequestParam(name = "nom", defaultValue = "Jesaispasencore", required = true) String nomp) {
		return nomp;
	}

	@PostMapping("/prix")
	public double affichagePrixProduit(
			@RequestParam(name = "prix", defaultValue = "15", required = true) double prixp) {
		return prixp;
	}

	
	@PostMapping("/calcul")
	public List<String> affichageCalculProduit(
			@RequestParam(name = "nom", defaultValue = "Jesaispasencore", required = true) String nomp,
			@RequestParam(name = "prixHT", defaultValue = "136", required = true) double prixht) {
		List<String> results = new ArrayList<>();
		double prixttc = 1.8 * prixht;
		String prixttcString = Double.toString(prixttc);
		results.add(nomp);
		results.add(prixttcString);
		return results;
	}

	@GetMapping("produits")
	public List<Produit> getProduits() {
		return ipr.findAll();
	}

	@PostMapping("saveProduit")
	public boolean saveProduit(@RequestBody Produit produit) {
		if (produit != null) {
			ipr.save(produit);
			return true;
		}
		return false;
		
	}
	@GetMapping("produit/{id}")
	public Produit getProduit(@PathVariable int id) {
		Produit p;
		try {
		p = ipr.findById(id).get();
		}catch(Exception e) {
			e.printStackTrace();
			p= null;
		}
			return p;
		}
	@DeleteMapping ("deletePrduit/{id}")
	public boolean deleteProduit(@PathVariable int id)
	{
		if(id >0) {
			ipr.deleteById(id);
			return true;
		}
		return false;
	}
	
	
}
