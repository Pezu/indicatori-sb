package com.xiia.indicatori.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "child_id", nullable = false)
	private Unit child;
	
	@Column(name = "article_id")
	private Integer articleId;
	
	@Column(name = "value")
	private Double value;
	
	
}
