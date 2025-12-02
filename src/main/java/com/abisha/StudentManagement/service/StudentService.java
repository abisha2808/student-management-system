package com.abisha.StudentManagement.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.abisha.StudentManagement.dto.StudentRequestDTO;
import com.abisha.StudentManagement.entity.Student;
import com.abisha.StudentManagement.exception.DuplicateEmailException;
import com.abisha.StudentManagement.exception.DuplicatePhoneException;
import com.abisha.StudentManagement.exception.StudentNotFoundException;
import com.abisha.StudentManagement.repository.StudentRepository;
import com.abisha.StudentManagement.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





@Service
public class StudentService {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

	@Autowired
	private StudentRepository studentRepo;
	
	public ApiResponse saveStudent(StudentRequestDTO dto) {
		logger.info("Adding new student: {}", dto.getName());
		
		Student s = new Student();
		s.setName(dto.getName());
	    s.setGender(dto.getGender());
	    s.setEmail(dto.getEmail());
	    s.setDept(dto.getDept());
	    s.setPhno(dto.getPhno());
			if(studentRepo.existsByEmail(s.getEmail())) {
				throw new DuplicateEmailException("Email already exists: " + s.getEmail());
			}
			
			if(studentRepo.existsByPhno(s.getPhno())) {
				throw new DuplicatePhoneException("Phone number already exists: " + s.getPhno());
			}
			 studentRepo.save(s);
			 return new ApiResponse(true, "student data saved sucessfully", s);
	
	}
	
	public ApiResponse getAll() {
		
		List<Student> s = studentRepo.findAll();
		if(s != null) {
			return new ApiResponse(true, "Students retrieved successfully", s);
		}
		return new ApiResponse(false, "No Student found", null);
	}
	
	public ApiResponse getById(Integer id) {
		logger.info("Request received to fetch student by id {}", id);

		Optional<Student> stud= studentRepo.findById(id);
		if(!stud.isPresent()) {
			logger.warn("Student id {} not found", id);

			throw new StudentNotFoundException("Student with id not found");

		}
		Student data = stud.get();
		return new ApiResponse(true,"Student found",data);

	}
	
	public ApiResponse deleteById(Integer id) {
		logger.info("Fetching student with id: {}", id);
		
		Optional<Student> stud = studentRepo.findById(id);
		if(stud.isPresent()) {
			studentRepo.deleteById(id);
	        logger.info("Student deleted successfully with id: {}", id);

			return new ApiResponse(true, "Student deleted successfully", null);
		}
		
		logger.warn("Student id {} not found", id);

		throw new StudentNotFoundException("Student with id is not found");
		
		
	}
	
	public ApiResponse deleteAll() {
		studentRepo.deleteAll();
		return new ApiResponse(true, "Student deleted successfully", null);
	}

	public ApiResponse UpdateStudent(Integer id, StudentRequestDTO dto) {
      
		logger.info("Updating student with id: {}", id);
		
	    
	    Student existingStudent = studentRepo.findById(id)
	            .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));

		
	    if (studentRepo.existsByEmailAndIdNot(dto.getEmail(), id)) {
	        throw new DuplicateEmailException("Email already exists: " + dto.getEmail());
	    }

	   
	    if (studentRepo.existsByPhnoAndIdNot(dto.getPhno(), id)) {
	        throw new DuplicatePhoneException("Phone number already exists: " + dto.getPhno());
	    }

	    existingStudent.setName(dto.getName());
	    existingStudent.setGender(dto.getGender());
	    existingStudent.setEmail(dto.getEmail());
	    existingStudent.setDept(dto.getDept());
	    existingStudent.setPhno(dto.getPhno());

	   
	    studentRepo.save(existingStudent);

	    return new ApiResponse(true, "Student data updated successfully", existingStudent);
	    
		
	}

	public ApiResponse getByName(String name) {
		logger.info("Fetching student with name: {}", name);
		
		List<Student> stud = studentRepo.findByName(name); 
		if(!stud.isEmpty()) {
			
			
			return new ApiResponse(true, "Student data is retrived succussfully", stud);
		}
		logger.error("Error in Fetching student with id: {}", name);
		
		throw new StudentNotFoundException("Student with name is not found");
	}
	public ApiResponse getByDept(String dept) {
		logger.info("Fetching student with dept: {}", dept);
		
		List<Student> stud = studentRepo.findByDept(dept); 
		if(!stud.isEmpty()) {
			return new ApiResponse(true, "Student data is retrived succussfully", stud);
		}
		logger.error("Error in Fetching student with dept: {}", dept);
		
		throw new StudentNotFoundException("Student with dept not found");
	}
	
	public ApiResponse getByEmail(String email) {
		logger.info("Fetching student with email: {}", email);
		
		Optional<Student> stud = studentRepo.findByEmail(email); 
		if(stud.isPresent()) {
			return new ApiResponse(true, "Student data is retrived succussfully", stud);
		}
		logger.info("Fetching student with email: {}", email);
		
		throw new StudentNotFoundException("Student with email is not found");
	}
	public ApiResponse getByPhno(String phno) {
		logger.info("Fetching student with phone: {}", phno);
		
		Optional<Student> stud = studentRepo.findByPhno(phno); 
		if(stud.isPresent()) {
			return new ApiResponse(true, "Student data is retrived succussfully", stud.get());
		}
		logger.error(" Error in Fetching student with phone: {}", phno);
		
		throw new StudentNotFoundException("Student with phone number is not found");
	}
    public ApiResponse getAllPage(int pageNumber,int size) {
    	
    	Pageable p = PageRequest.of(pageNumber, size);
    	Page<Student> s = studentRepo.findAll(p);
    	if(s != null) {
    		return new ApiResponse(true, "Student data is retrived succussfully", s); 
    	}
    	throw new StudentNotFoundException("Student data is empty");
    }
    public ApiResponse sortStudents(String field, String direction) {
    	Sort sort;
    	if(direction.equalsIgnoreCase("asc")) {
    		sort = Sort.by(field).ascending();
    	}else if(direction.equalsIgnoreCase("desc")) {
    		sort = Sort.by(field).descending();
    	}else {
    		
    		throw new StudentNotFoundException("Invalid direction (use asc or desc)");
    	}
    	List<Student> list = studentRepo.findAll(sort);
    	
    	if(list !=null && !list.isEmpty()) {
    		return new ApiResponse(true, "Student data is retrived succussfully", list);
    	}
    	throw new StudentNotFoundException("Student data is empty");
    }
    
    public ApiResponse getByNameAndDept(String name ,String dept) {
    	logger.info("Fetching student with Name and Dept : {}", name, dept);
		
    	
    	List<Student> s= studentRepo.findByNameAndDept(name, dept);
    	if(!s.isEmpty()) {
    		return new ApiResponse(true, "Student data is retrived succussfully", s);
    	}
    	logger.error("Error in Fetching student with name and dept: {}", name, dept);
		
    	throw new StudentNotFoundException("Student data is empty");
    }
    public ApiResponse getByNameAndGender(String name ,String gender) {
    	logger.info("Fetching student with Name and Dept : {}", name, gender);
		
    	
    	List<Student> s= studentRepo.findByNameAndGender(name, gender);
    	if(!s.isEmpty()) {
    		return new ApiResponse(true, "Student data is retrived succussfully", s);
    	}
    	logger.error("Error in Fetching student with name and dept: {}", name, gender);
		
    	throw new StudentNotFoundException("Student data is empty");
    }
    
    public ApiResponse getByGenderAndDept(String gender ,String dept) {
    	logger.info("Fetching student with Gender and Dept: {}", gender, dept);
		
    	List<Student> s= studentRepo.findByGenderAndDept(gender, dept);
    	if(!s.isEmpty()) {
    		return new ApiResponse(true, "Student data is retrived succussfully", s);
    	}
    	logger.error("Error in Fetching student with gender and dept: {}", gender , dept);
		
    	throw new StudentNotFoundException("Student not found");
    }
    
public ApiResponse getByNameGenderAndDept(String name ,String gender ,String dept) {
	logger.info("Fetching student with Name gender nad dept  : {}", name,gender,dept);
	
    	List<Student> s= studentRepo.findByNameAndGenderAndDept(name,gender, dept);
    	if(!s.isEmpty()) {
    		return new ApiResponse(true, "Student data is retrived succussfully", s);
    	}
    	logger.error("Error in Fetching student with Name gender and dept : {}", name,gender,dept);
		
    	throw new StudentNotFoundException("Student not found");
    }
    public ApiResponse getByPhnoFirstDigit(String firstDigit) {
    	logger.info("Fetching student with phno starting with : {}", firstDigit);
    	
    	List<Student> s = studentRepo.findByPhnoStartingWith(firstDigit);
    	if(! s.isEmpty()) {
    		return new ApiResponse(true, "Student data is retrived succussfully", s);
    	}
    	logger.error("Error in Fetching student with phno starting with : {}", firstDigit);
    	throw new StudentNotFoundException("Student not found");
    }
    
    public ApiResponse getByNameContaingString(String name) {
    	logger.info("Fetching student with Name containing specific stirng : {}", name);
		
    	
    	List<Student> s = studentRepo.findByNameContaining(name);
    	if(! s.isEmpty()) {
    		return new ApiResponse(true, "Student data is retrived succussfully", s);
    	}
    	logger.error("Error in Fetching student with Name containing specific stirng : {}", name);
		
    	throw new StudentNotFoundException("Student not found");
    }

}
