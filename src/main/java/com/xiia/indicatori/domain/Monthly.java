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
@Table(name = "monthly")
public class Monthly {

	@Id
	@Column(name = "month")
	private String month;
	
	@Id
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Id
	@Column(name = "type_id")
	private Integer typeId;
	
	@Column(name = "value")
	private Double value;
	
	
}
