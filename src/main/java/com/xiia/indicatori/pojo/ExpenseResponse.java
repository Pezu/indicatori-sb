package com.xiia.indicatori.pojo;

import java.math.BigInteger;
import java.util.List;

import com.xiia.indicatori.domain.Expense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponse{

	private List<Expense> expenses;
	private BigInteger count;
	
}
