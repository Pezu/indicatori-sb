package com.xiia.indicatori.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SplitRequest{

	private Integer parentUnitId;
	private String expenseId;
	private Integer articleId;
	private String splitCode;
	private Integer splitId;
	private String month;
	private Integer categoryId;
	private Integer groupId;
	
}
