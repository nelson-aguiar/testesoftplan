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
public class PersonDTOV2 extends AbstractPerson {
	
	@JsonProperty("name")
	@NotBlank(message = "Nome deve ser preenchido")
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
	@NotNull(message = "Data de nascimento deve ser preenchida")
	private Date dateOfBirth;
	
	@JsonProperty("naturalness")
	private String naturalness;
	
	@JsonProperty("nationality")
	private String nationality;
	
	@JsonProperty("document")
	@NotBlank(message = "CPF deve ser preenchido")
	@CPF(message = "CPF inválido")
	private String document;
	
	@JsonProperty("registred_at")
	private Date registredAt;
	
	@JsonProperty("updated_at")
	private Date updatedAt;
	
	@JsonProperty("address")
	private AddressDTO address;
	
//	@JsonProperty("postal_code")
//	@NotBlank(message = "CEP deve ser preenchido")
//	private String postalCode;
//	
//	@JsonProperty("street")
//	@NotBlank(message = "Rua deve ser preenchido")
//	private String street;
//	
//	@JsonProperty("number")
//	@NotNull(message = "Número deve ser preenchido")
//	private Integer number;
//	
//	@JsonProperty("complement")
//	private String complement;
//	
//	@JsonProperty("district")
//	@NotBlank(message = "Bairro deve ser preenchido")
//	private String district;
//	
//	@JsonProperty("city")
//	@NotBlank(message = "Cidade deve ser preenchido")
//	private String city;
//	
//	@JsonProperty("state")
//	@NotBlank(message = "Estado deve ser preenchido")
//	private String state;

}
