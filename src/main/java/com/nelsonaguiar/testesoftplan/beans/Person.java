package com.nelsonaguiar.testesoftplan.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="person")
public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email")
	private String email;

	@Column(name = "gender")
	private char gender;
	
	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;
	
	@Column(name = "naturalness")
	private String naturalness;
	
	@Column(name = "nationality")
	private String nationality;
	
	@Column(name = "document", unique = true)
	private String document;
	
	@Column(name = "registred_at")
	private Date registredAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name="address")
	private String address;
}
