package com.abisha.StudentManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PageController {
	@GetMapping("/")
	public String indexPage() {
	    return "index"; // index.html
	}
	@GetMapping("/add-student")
	public String addPage() {
	    return "add-student"; 
	}
	@GetMapping("/view-students")
	public String viewPage() {
	    return "view-students";
	}
	@GetMapping("/update-student")
	public String updatePage() {
	    return "update-student";
	}

}
