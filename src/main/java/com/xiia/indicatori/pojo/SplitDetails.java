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
	private Integer parent;
	private String split;
	private String month;
	private Boolean updateWeight;
	
}
