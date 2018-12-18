package com.xiia.indicatori.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedCreateRequest{

	@JsonProperty("artList")
	private List<String> artList;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("accountId")
	private Integer accountId;
	
	@JsonProperty("pret")
	private Double pret;
	
}
