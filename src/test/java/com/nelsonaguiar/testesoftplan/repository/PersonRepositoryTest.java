package com.nelsonaguiar.testesoftplan.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nelsonaguiar.testesoftplan.beans.Person;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application-test.properties")
@Slf4j
class PersonRepositoryTest {
	
	@Autowired
	private PersonRepository repository;

	@Test
	void testInit() {
		assertEquals(this.repository.findAll().iterator().hasNext(), true);
	}
	
	@Test
	public void testInsert() {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		
		try {
			d = sdf.parse("24/12/19852");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		Person p = new Person(1000L, "Nelson", null, 'M', d, "Curitibano", "brasileiro", "040-538.019-40", new Date(System.currentTimeMillis()), null, null);
		
		this.repository.save(p);
		
		assertEquals(this.repository.findAll().iterator().hasNext(), true);
		
		log.error(this.repository.findAll().toString());
	}
	
	

}
