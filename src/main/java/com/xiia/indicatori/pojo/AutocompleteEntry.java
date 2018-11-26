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

	private String name;
	private List<ArticleEntry> articles;
	
}
