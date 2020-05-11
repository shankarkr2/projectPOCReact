package com.yash.pma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.yash.pma.domain.Project;
import com.yash.pma.service.PMAService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
@CrossOrigin
public class PMAController {

	@Autowired
	private PMAService pmaService;

	@PostMapping("")
	public ResponseEntity<?> addProject(@Valid @RequestBody Project project, BindingResult result) {

		if (result.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();

			for (FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}

		Project newPT = pmaService.saveOrUpdateProjectTask(project);

		return new ResponseEntity<Project>(newPT, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public Iterable<Project> getAllProjects() {
		return pmaService.findAll();
	}

	@GetMapping("/{pt_id}")
	public ResponseEntity<?> getProjectById(@PathVariable Long pt_id) {
		Project projectTask = pmaService.findById(pt_id);
		return new ResponseEntity<Project>(projectTask, HttpStatus.OK);
	}

	@DeleteMapping("/{pt_id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long pt_id) {
		pmaService.delete(pt_id);

		return new ResponseEntity<String>("Project Task deleted", HttpStatus.OK);
	}

}
