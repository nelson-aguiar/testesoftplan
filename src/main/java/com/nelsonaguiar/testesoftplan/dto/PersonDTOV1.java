package com.nelsonaguiar.testesoftplan.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.Nullable;
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
	@Nullable
	private String email;

	@JsonProperty("gender")
	private char gender;
	
	@JsonProperty("date_of_birth")
	@JsonFormat(pattern="dd/MM/yyyy")
	@Past
	@NotNull
	private Date dateOfBirth;
	
	@JsonProperty("naturalness")
	private String naturalness;
	
	@JsonProperty("nationality")
	private String nationality;
	
	@JsonProperty("document")
	@NotBlank
	@CPF(message = "CPF inválido")
	private String document;
	
	@JsonProperty("registred_at")
	private Date registredAt;
	
	@JsonProperty("updated_at")
	private Date updatedAt;
}
