package com.xiia.indicatori.pojo;

import java.math.BigInteger;
import java.util.List;

import com.xiia.indicatori.domain.Fixed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedResponse{

	private List<Fixed> fixed;
	private BigInteger count;
	
}
