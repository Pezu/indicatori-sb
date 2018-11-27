package com.xiia.indicatori.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseFilter{

	private String month;
	private Integer groupId;
	private Integer categoryId;
	private Integer articleId;
	private Integer unitId;
	
}
