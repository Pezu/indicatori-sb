package com.xiia.indicatori.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FixedMoveRequest{

	Integer fixedId;
	Integer sourceAccountId;
	Integer destinationAccountId;
	
}
