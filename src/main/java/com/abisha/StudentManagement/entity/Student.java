package com.abisha.StudentManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="students")
public class Student {
    
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be empty")
	private String name;
	@NotBlank(message = "Gender cannot be empty")
	private String gender;
	@NotBlank(message = "Email cannot be empty")
	@Email(message="Invalid email format")
	@Pattern(
			   regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
			   message = "Email must contain domain like .com or .in"
			)
	private String email;
	@NotBlank(message = "Deptarment cannot be empty")
	private String dept;
	@NotBlank(message = "Phone number cannot be empty")
	@Size(min=10,max=10, message="Phnone number must be 10 digits")
	private String phno;
	
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", dept=" + dept
				+ ", phno=" + phno + "]";
	}
	
	
}
