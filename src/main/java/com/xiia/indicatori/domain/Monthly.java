package com.xiia.indicatori.domain;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "monthly")
public class Monthly{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "type_id")
	private Integer typeId;
	
	@Column(name = "value")
	private Double value;

	
}
