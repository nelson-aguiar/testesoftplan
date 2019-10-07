package com.nelsonaguiar.testesoftplan.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PersonDTOV2 extends AbstractPerson {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("email")
	@Email
	private String email;

	@JsonProperty("gender")
	private char gender;
	
	@JsonProperty("date_of_birth")
	private Date dateOfBirth;
	
	@JsonProperty("naturalness")
	private String naturalness;
	
	@JsonProperty("nationality")
	private String nationality;
	
	@JsonProperty("document")
	private String document;
	
	@JsonProperty("registred_at")
	private Date registredAt;
	
	@JsonProperty("updated_at")
	private Date updatedAt;
	
	@JsonProperty("address")
	@NotBlank
	private String address;

}
