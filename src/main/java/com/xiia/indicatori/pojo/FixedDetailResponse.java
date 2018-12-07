package com.xiia.indicatori.pojo;

import java.math.BigInteger;
import java.util.List;

import com.xiia.indicatori.domain.Fixed;
import com.xiia.indicatori.domain.FixedHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedDetailResponse{

	private List<FixedHistory> fixed;
	private String code;
	private String name;
	
}
