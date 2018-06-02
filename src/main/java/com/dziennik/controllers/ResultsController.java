package com.dziennik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dziennik.domain.Result;
import com.dziennik.exceptions.ResultNotFoundException;
import com.dziennik.services.ResultService;

@RestController
public class ResultsController {

	@Autowired
	private ResultService resultService;

	@GetMapping("/result/{id}")
	public Result getResultById(@PathVariable("id") int id) {
		Result result = resultService.findById(id);

		if (result == null) {
			throw new ResultNotFoundException(id);
		}
		return result;
	}

	@PostMapping("/teacher/results/edit/addResult")
	@ResponseStatus(HttpStatus.CREATED)
	public Result addResult(@RequestBody Result result) {
		resultService.create(result);
		return result;
	}

	@GetMapping("/teacher/result/edit/delete/{id}")
	public void deleteResult(@PathVariable("id") int id) {
		Result result = resultService.findById(id);
		resultService.delete(result);
	}
}
