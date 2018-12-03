package com.xiia.indicatori.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SplitChild {

	private Integer unitId;
	private String unitName;
	private Double value;
	private Double weight;
	private Boolean updateWeight;
	
}

