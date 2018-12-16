package com.xiia.indicatori.pojo.rapports;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryLine{

	private String code;
	private String name;
	private Double pacients;
	private Double beds;
	private Double days;
	private Double ammount;
	private Double ammPacient; 
	private Double ammDay; 
	private Double ammBed; 
	private Double bedUsage; 
	private Double hspDuration; 
	private Boolean total;
	
}
