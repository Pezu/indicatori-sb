package com.medivac.indicatori.domain;

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
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "direct")
	private Short direct;
	
	@Column(name = "article_id")
	private Long articleId;
	
	@Column(name = "unit_id")
	private Long unitId;
	
	@Column(name = "split_id")
	private Date splitId;
	
	
}
