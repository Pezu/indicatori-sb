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
@Table(name = "default_split")
public class DefaultSplit {

	//article_id.unit_id
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "split_code")
	private String splitCode;
		
}