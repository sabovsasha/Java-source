package com.telusko;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AlienRepository extends CrudRepository<Alien, Integer>{
	
	List<Alien> findByName(String name);
	List<Alien> findByIdGreaterThan(int id);
	@Query("from Alien where name=?1 order by points")
	List<Alien> findByNameSorted(String name);

}
