package com.inti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Magasin;
import com.inti.model.Produit;
import com.inti.repository.IMagasinRepository;
import com.inti.repository.IproduitRepository;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("magasin")
@Slf4j
public class MagasinController {
	@Autowired
	IMagasinRepository imr;

	@Autowired
	IproduitRepository ipr;

	
	@GetMapping("magasins")
	public List<Magasin> getMagasin() {
		return imr.findAll();
	}

	@PostMapping("save")
	public boolean saveMagasin(@RequestBody Magasin magasin) {
		if (magasin != null) {
			imr.save(magasin);
			return true;
		}
		return false;

	}

	@GetMapping("magasins/{id}")
	public Magasin getMagasin(@PathVariable int id) {
		Magasin m;
		try {
			m = imr.findById(id).get();
		} catch (Exception e) {
			e.printStackTrace();
			m = null;
		}
		return m;
	}

	@DeleteMapping("deleteMagasin/{id}")
	public boolean deleteMagasin(@PathVariable int id) {
		int maxID = imr.findMaxID();
		if (id > 0 && id< maxID) {
			imr.deleteById(id);
			log.info("la fonction a marche");
			return true;
		}
		log.info("la fonction a rate");
		return false;
	}
	@PutMapping("update/{id}")
	public Magasin updateMAgasin(@RequestBody Magasin nouveauMagasin,@PathVariable int id) {
		return imr.findById(id)
				.map(magasin -> {
					magasin.setNom(nouveauMagasin.getNom());
					magasin.setAdresse(nouveauMagasin.getAdresse());
					magasin.setCp(nouveauMagasin.getCp());
					magasin.setVille(nouveauMagasin.getVille());
					return imr.save(magasin);
				})
				.orElseGet(() -> {
					return imr.save(nouveauMagasin);
				});
	}
	
	@PostMapping("associerProduit/{id}")
	public boolean associerProduitToMagasin(@PathVariable int id,Produit produit) {
		try {
		Magasin magasin = imr.findById(id).get();
		
		List<Produit> listeProduit = ipr.findAll();
		listeProduit.add(produit);
		
		magasin.setListeProduit(listeProduit);
		
		imr.save(magasin);
		return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
			return false;
	}
	@GetMapping("byName/{nom}")
	public Magasin getMagasinByNom(@PathVariable String nom) {
		return imr.findByNom(nom);
	}
	@GetMapping("byCpAndVille")
	public Magasin getMagasinByCpandVille(@RequestParam (name ="cp")  int cp,@RequestParam(name ="ville") String ville) {
		return imr.findByCpAndVille(cp, ville);
	}
	
	@GetMapping("updateAdresseCp")
	public void updateMagasinByAdresseAndCp(@RequestParam(name ="adresse") String adresse, @RequestParam (name ="cp")int cp, @RequestParam(name ="id") int id) {
		 imr.updateAdressAndCpById(adresse, cp, id);
	}
	
}