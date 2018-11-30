package com.xiia.indicatori.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "percentages")
public class Percentage {

	//parent_id.child_id.article_id
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private String id;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "child_id")
	private Integer childId;
	
	@Column(name = "article_id")
	private Integer articleId;
	
	@Column(name = "value")
	private Double value;
	
	
}