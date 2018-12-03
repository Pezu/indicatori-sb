package com.xiia.indicatori.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SplitDetails{

	private List<SplitChild> children;
	private Integer parentUnitId;
	private String expenseId;
	private Integer splitId;
	private String splitCode;
	private String month;
	private Integer articleId;
	private Integer categoryId;
	private Integer groupId;
	private Boolean updateWeight;
	
}
