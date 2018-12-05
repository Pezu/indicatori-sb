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
@Table(name = "history")
public class FixedHistory extends Updater{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "source_account_id", nullable = false)
	private Account sourceAccount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "destination_account_id", nullable = false)
	private Account destinationAccount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fixed_id", nullable = false)
	private Fixed fixed;
	
	
}