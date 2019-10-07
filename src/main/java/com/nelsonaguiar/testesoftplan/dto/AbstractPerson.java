package com.nelsonaguiar.testesoftplan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractPerson {

	@JsonProperty("id")
	private Long id;
}
