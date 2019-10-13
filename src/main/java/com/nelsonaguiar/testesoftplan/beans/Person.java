package com.nelsonaguiar.testesoftplan.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

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

	@Column(name = "name", nullable = false, table = "person")
	private String name;
	
	@Column(name = "email", table = "person")
	private String email;

	@Column(name = "gender", table = "person")
	private char gender;
	
	@Temporal(TemporalType.DATE)
	@Past
	@Column(name = "date_of_birth", nullable = false, table = "person")	
	private Date dateOfBirth;
	
	@Column(name = "naturalness", table = "person")
	private String naturalness;
	
	@Column(name = "nationality", table = "person")
	private String nationality;
	
	@Column(name = "document", unique = true, table = "person")
	private String document;
	
	@Column(name = "registred_at", table = "person")
	private Date registredAt;
	
	@Column(name = "updated_at", table = "person")
	private Date updatedAt;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id", name = "person_id")
	private Address address;	
}
