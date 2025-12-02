package com.abisha.StudentManagement.repository;


import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.abisha.StudentManagement.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>  {
     
	
	// save(Student)
	//findById(Integer)
	//findAll(Student)
	//deleteAll(student)
	//deletById(Integer)
	
	boolean existsByEmail(String email);
	
	boolean existsByPhno(String phono);
	
	boolean existsByEmailAndIdNot(String email, int id);
	boolean existsByPhnoAndIdNot(String phno, int id);
	
	List<Student> findByName(String name);
	
	List<Student> findByDept(String dept);
	
	Optional<Student> findByEmail(String email);
	
	Optional<Student> findByPhno(String phno);
	
	Page<Student> findAll(Pageable pageable);
	
	List<Student> findAll(Sort sort);
	
	List<Student> findByNameContaining(String name);
	
	List<Student> findByPhnoStartingWith(String firstDigit);
	
	List<Student> findByNameAndGenderAndDept(String name, String gender,String dept);
	
	List<Student> findByGenderAndDept(String Gender, String Dept);
	
	List<Student> findByNameAndDept(String Name, String Dept);
	List<Student> findByNameAndGender(String Name, String gender);


}
