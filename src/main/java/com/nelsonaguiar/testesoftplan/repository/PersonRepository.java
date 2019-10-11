package com.nelsonaguiar.testesoftplan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nelsonaguiar.testesoftplan.beans.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
	
	Optional<List<Person>> findAllByNameContaining(String name);
	
	List<Person> findAll();
}
