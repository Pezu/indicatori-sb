package com.xiia.indicatori.domain;

import java.util.Date;

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
@Table(name = "expenses")
public class Expense extends Updater {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "unit_id")
	private Long unitId;
	
	@Column(name = "article_id")
	private Long articleId;
	
	@Column(name = "group_id")
	private Long groupId;
	
	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "direct")
	private Boolean direct;
	
	@Column(name = "split_id")
	private Date splitId;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "description")
	private String description;
	
	
}
