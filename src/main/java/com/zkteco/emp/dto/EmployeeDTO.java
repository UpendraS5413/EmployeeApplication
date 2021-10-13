package com.zkteco.emp.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
	private String id;

	@NotNull(message = "please add firstName")
	private String firstName;

	@NotNull(message = "please add lastName")
	private String lastName;

	@NotNull(message = "please add empCode")
	private String empCode;

	@NotNull(message = "please add desgnation")
	private String designation;

	@NotNull(message = "please add emailId")
	private String emailId;

	@NotNull(message = "please add date of birth")
	private String dob;

	@NotNull(message = "please add mobile")
	private String Mobile;

	private Date createDate;
	private Date updateDate;
}
