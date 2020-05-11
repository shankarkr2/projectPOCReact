package com.yash.pma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.pma.domain.Project;
import com.yash.pma.repository.PMARepository;

@Service
public class PMAService {

	@Autowired
	private PMARepository pmaRepository;

	public Project saveOrUpdateProjectTask(Project projectTask) {

		if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
			projectTask.setStatus("TO_DO");
		}

		return pmaRepository.save(projectTask);
	}

	public Iterable<Project> findAll() {
		return pmaRepository.findAll();
	}

	public Project findById(Long id) {
		return pmaRepository.getById(id);
	}

	public void delete(Long id) {
		Project projectTask = findById(id);
		pmaRepository.delete(projectTask);
	}
}
