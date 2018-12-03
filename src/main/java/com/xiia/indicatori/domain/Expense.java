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

	//article_id.parent_id.month.child_id pentru cele generate sau uuid pentru cele introduse
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
	private Date splitId;
	
	@Column(name = "parent_id")
	private String parentId;
	
	@Column(name = "original_parent_id")
	private String originalParentId;
	
	@Column(name = "description")
	private String description;
		
}