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
public class AutocompleteEntry{

	private Integer id;
	private String code;
	private String name;
	private String groupCode;
	private String categoryCode;
	
}
