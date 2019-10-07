package com.nelsonaguiar.testesoftplan.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nelsonaguiar.testesoftplan.beans.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
	
	List<Person> findAll();

}
