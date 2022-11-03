package com.inti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inti.model.Magasin;


public interface IMagasinRepository extends JpaRepository<Magasin, Integer>{

	// select * from magasin where nom := nom
	Magasin findByNom(String nom);
	// select * from magasin where cp := cp and ville :=ville
	Magasin findByCpAndVille(int cp, String ville);
	
	@Query(value= "select max(id) from magasin_spring", nativeQuery = true)
	int findMaxID();
	
}
