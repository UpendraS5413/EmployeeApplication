package com.zkteco.emp.entity;

import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Employee")
public class Employee {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "emp_id", length = 50, nullable = false)
	private String empId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "emp_code")
	private String empCode;

	@Column(name = "designation")
	private String designation;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "dob")
	private String dob;

	@Column(name = "mobile")
	private String mobile;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_name")
	private Date createDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updateDate;

}
