package com.xiia.indicatori.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	//parent_expense_id.unit_id pentru cele generate sau uuid pentru cele introduse
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "article_id")
	private Integer articleId;
	
	@Column(name = "group_id")
	private Integer groupId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "direct")
	private Boolean direct;
	
	@Column(name = "split_id")
	private Integer splitId;
	
	@Column(name = "parent_id")
	private String parentId;
	
	@Column(name = "parent_unit_id")
	private Integer parentUnitId;
	
	@Column(name = "original_parent_id")
	private String originalParentId;
	
	@Column(name = "description")
	private String description;
		
}