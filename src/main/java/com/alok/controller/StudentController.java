package com.alok.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.alok.Entity.StudentEntity;
import com.alok.bindings.Student;
import com.alok.repo.StudentRepository;

@Controller
public class StudentController {
	@Autowired
	private StudentRepository repo;
	
	//method to load Student Form
	@GetMapping("/")
	public String loadForm(Model model) {
		loadFormData(model);
		
		return "index";
	}


	private void loadFormData(Model model) {
		List<String> coursesList=new ArrayList<>();
		coursesList.add("java");
		coursesList.add("Devops");
		coursesList.add("AWS");
		coursesList.add("Python");
		
		List<String> timingsList=new ArrayList<>();
		timingsList.add("Morning");
		timingsList.add("After-noon");
		timingsList.add("Evening");
		Student student=new Student();
		
		model.addAttribute("courses",coursesList);
		model.addAttribute("timings",timingsList);
		model.addAttribute("student",student);
	}
	
	
	//method to save the Student Form data
	@PostMapping("/save")
	public String handleSubmit(Student s, Model model) {
		System.out.println(s);
		//logic to save
		
		StudentEntity entity=new StudentEntity();
		//copy data from binding object to entity object
		BeanUtils.copyProperties(s, entity);
		
		entity.setTimings(Arrays.toString(s.getTimings()));
		
		repo.save(entity);
		model.addAttribute("msg","Student saved");
		
		loadFormData(model);
		
		
		
		
		return "index";
		
	}
	
	
	
	//method to display the student data
	@GetMapping("/viewStudents")
	public String getStudentsData(Model model) {
		
		//logic to fetch the student data
		
		List<StudentEntity> studentsList = repo.findAll();
		model.addAttribute("students", studentsList);
		
		return "data";
	}

}
