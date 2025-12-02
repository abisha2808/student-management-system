package com.abisha.StudentManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class StudentRequestDTO {
	
	@NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Gender cannot be empty")
    private String gender;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    @Pattern(
    		   regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
    		   message = "Email must contain domain like .com or .in"
    		)
    private String email;

    @NotBlank(message = "Department cannot be empty")
    private String dept;

    @NotNull(message = "Phone number cannot be empty")
    @Size(min = 10, max = 10, message = "Phone must be 10 digits")
    private String phno;   // DTO keeps phone as String

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
    
    

}
