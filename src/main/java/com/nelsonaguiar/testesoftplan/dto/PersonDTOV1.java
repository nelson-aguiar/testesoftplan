package com.nelsonaguiar.testesoftplan.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class PersonDTOV1 extends AbstractPerson {

	@JsonProperty("name")
	@NotBlank
	private String name;
	
	@JsonProperty("email")
	@Email
	@NotBlank
	private String email;

	@JsonProperty("gender")
	private char gender;
	
	@JsonProperty("date_of_birth")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dateOfBirth;
	
	@JsonProperty("naturalness")
	private String naturalness;
	
	@JsonProperty("nationality")
	private String nationality;
	
	@JsonProperty("document")
	@NotBlank
	private String document;
	
	@JsonProperty("registred_at")
	private Date registredAt;
	
	@JsonProperty("updated_at")
	private Date updatedAt;
}
