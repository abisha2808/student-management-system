package com.abisha.StudentManagement.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abisha.StudentManagement.dto.StudentRequestDTO;
import com.abisha.StudentManagement.entity.Student;
import com.abisha.StudentManagement.response.ApiResponse;
import com.abisha.StudentManagement.service.StudentService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class Studentcontroller {
    
	@Autowired
	private StudentService studentSer;
	
	
	@PostMapping("/save")
	public ApiResponse saveStudent(@Valid @RequestBody StudentRequestDTO dto) {
		
		return studentSer.saveStudent(dto);
	}
	
	@GetMapping("/getAll")
	public ApiResponse getAll(){
		 return studentSer.getAll();
	}
	
	@GetMapping("/getById")
	public ApiResponse getById(@RequestParam Integer id) {
		 return studentSer.getById(id);
		 
	}
	
	@DeleteMapping("/deleteById")
	public ApiResponse deleteById(@RequestParam Integer id) {
		return studentSer.deleteById(id);
	}
	
	@DeleteMapping("/deleteAll")
	public ApiResponse deleteAll() {
		return studentSer.deleteAll();
	}	
	
	@PutMapping("/update")
	public ApiResponse updateStudent(@RequestParam Integer id, @Valid @RequestBody StudentRequestDTO dto) {
		return studentSer.UpdateStudent(id, dto);
	}
	@GetMapping("/getByName")
	public ApiResponse getByName(@RequestParam String name) {
		return studentSer.getByName(name);
	}
	@GetMapping("/getByDept")
	public ApiResponse getByDept(@RequestParam String dept) {
		return studentSer.getByDept(dept);
	}
	@GetMapping("/getByEmail")
	public ApiResponse getByEmail(@RequestParam String email) {
		return studentSer.getByEmail(email);
	}
	
	@GetMapping("/getByPhno")
	public ApiResponse getByPhno(@RequestParam String phno) {
		return studentSer.getByPhno(phno);
	}
	@GetMapping("/getAllPage")
	public ApiResponse getAllPage(@RequestParam int pageNumber,@RequestParam int size) {
		 return studentSer.getAllPage(pageNumber, size);
	}
	@GetMapping("/sortStudent")
	public ApiResponse sortStudent(@RequestParam String field,@RequestParam String direction) {
		return studentSer.sortStudents(field, direction);
	}
	@GetMapping("/getByNameAndDept")
	public ApiResponse getByNameAndDept(@RequestParam String name, @RequestParam String dept) {
		return studentSer.getByNameAndDept(name, dept);
	}
	@GetMapping("/getByNameAndGender")
	public ApiResponse getByNameAndGender(@RequestParam String name, @RequestParam String gender) {
		return studentSer.getByNameAndGender(name, gender);
	}
	
	@GetMapping("/getByGenderAndDept")
	public ApiResponse getByGenderAndDept(@RequestParam String gender, @RequestParam String dept) {
		return studentSer.getByGenderAndDept(gender, dept);
	}
	
	@GetMapping("/getByNameGenderAndDept")
	public ApiResponse getByNameGenderAndDept(@RequestParam String name,@RequestParam String gender, @RequestParam String dept) {
		return studentSer.getByNameGenderAndDept(name,gender, dept);
	}
	@GetMapping("/getByPhnoFirstDigit")
	public ApiResponse getByPhnoFirstDigit(String firstDigit) {
		return studentSer.getByPhnoFirstDigit(firstDigit);
	}
	@GetMapping("/getByNameContainingString")
	public ApiResponse getByNameContaining(String name) {
		return studentSer.getByNameContaingString(name);
	}
	

}
