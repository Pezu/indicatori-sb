package com.xiia.indicatori.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleEntry{

	private Integer id;
	private String code;
	private String name;
	
}
