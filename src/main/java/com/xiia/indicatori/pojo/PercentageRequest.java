package com.xiia.indicatori.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PercentageRequest{

	private Integer parentId;
	private Integer childId;
	private Integer articleId;
	
}
