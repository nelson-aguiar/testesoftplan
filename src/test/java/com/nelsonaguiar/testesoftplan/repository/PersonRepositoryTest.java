package com.nelsonaguiar.testesoftplan.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nelsonaguiar.testesoftplan.beans.Person;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations="classpath:application-test.properties")
@Slf4j
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class PersonRepositoryTest {
	
	@Autowired
	private PersonRepository repository;
	
	private Person p;

	@Test
	@Order(1)
	void testInit() {
		assertEquals(this.repository.findAll().iterator().hasNext(), true);
	}
	
	@Test
	@Order(2)
	public void testInsert() {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;		
		try {
			this.p = new Person();
			p.setId(1000L);
			p.setName("Nelson");
			p.setEmail(null);
			p.setDocument("040-538.019-40");
			p.setDateOfBirth(d);
			p.setRegistredAt( new Date(System.currentTimeMillis()));
			this.repository.save(p);			
			this.p = this.repository.findById(1000L).get();
			
			assertAll("Testing person Insert", 
					() -> assertEquals(this.p.getId(), 1000L, "Id not found"),
					() -> assertTrue(this.p.getName().equalsIgnoreCase("Nelson"), "Name not found"),
					() -> assertEquals(this.p.getGender(), 'M', "gender not found")
			);
					
			log.error(this.repository.findAllByNameContaining("Nels").toString());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	@Test
	@Order(3)
	public void  testDelete() {
		Person p = this.repository.findById(1000L).get();
		this.repository.delete(p);		
		assertFalse(this.repository.findById(1000L).isPresent());
	}
	
	

}
