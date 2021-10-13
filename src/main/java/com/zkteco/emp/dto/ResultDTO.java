package com.zkteco.emp.dto;

import com.zkteco.emp.counts.ErrorCounts;
import com.zkteco.emp.counts.SuccessCounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTO {
	private String code;
	private String message;
	private Object data;

	public void setData(SuccessCounts count, ErrorCounts count1) {
		this.data = count;
		this.data = count1;
	}

}
