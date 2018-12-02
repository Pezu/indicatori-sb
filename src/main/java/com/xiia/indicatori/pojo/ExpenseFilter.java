package com.xiia.indicatori.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseFilter{

	@JsonProperty("month")
	private String month;
	
	@JsonProperty("groupId")
	private Integer groupId;
	
	@JsonProperty("categoryId")
	private Integer categoryId;

	@JsonProperty("articleId")
	private Integer articleId;
	
	@JsonProperty("unitId")
	private Integer unitId;
	
	@JsonProperty("pageSize")
	private Integer pageSize;
	
	@JsonProperty("pageNo")
	private Integer pageNo;
	
	@JsonProperty("root")
	private Integer root;
	
	@JsonProperty("split")
	private Integer split;
	
}
