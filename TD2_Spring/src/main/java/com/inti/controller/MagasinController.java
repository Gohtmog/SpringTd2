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
import org.springframework.web.bind.annotation.RestController;

import com.inti.model.Magasin;
import com.inti.repository.IMagasinRepository;

import lombok.extern.java.Log;

@RestController
@RequestMapping("magasin")
public class MagasinController {
	@Autowired
	IMagasinRepository imr;

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

	@DeleteMapping("deletePrduit/{id}")
	public boolean deleteProduit(@PathVariable int id) {
		if (id > 0) {
			imr.deleteById(id);
			return true;
		}
		return false;
	}
}