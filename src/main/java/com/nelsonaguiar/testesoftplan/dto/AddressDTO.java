package com.nelsonaguiar.testesoftplan.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AddressDTO {
	
	@JsonProperty("postal_code")
	@NotBlank(message = "CEP deve ser preenchido")
	private String postalCode;
	
	@JsonProperty("street")
	@NotBlank(message = "Rua deve ser preenchido")
	private String street;
	
	@JsonProperty("number")
	@NotNull(message = "Número deve ser preenchido")
	private Integer number;
	
	@JsonProperty("complement")
	private String complement;
	
	@JsonProperty("district")
	@NotBlank(message = "Bairro deve ser preenchido")
	private String district;
	
	@JsonProperty("city")
	@NotBlank(message = "Cidade deve ser preenchido")
	private String city;
	
	@JsonProperty("state")
	@NotBlank(message = "Estado deve ser preenchido")
	private String state;

}
