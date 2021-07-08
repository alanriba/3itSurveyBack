package com.it.survey.demo.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.survey.demo.dto.SurveyDTO;
import com.it.survey.demo.service.ISurveyService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/")

public class SurveyController {
	
	@Autowired
	private ISurveyService service;

	@GetMapping(value = "/survey")
	public ResponseEntity<List<?>> getSurvey() {
		return new ResponseEntity<List<?>>(service.list(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/stadistics")
	public ResponseEntity<List<?>> getEstadistics() throws ExecutionException, InterruptedException {
		return new ResponseEntity<List<?>>(service.surveyStadistics(), HttpStatus.OK);
	}

	@PostMapping(value="/survey")
	public ResponseEntity<Boolean> add(@RequestBody SurveyDTO survey){
        return new ResponseEntity<Boolean>(service.add(survey), HttpStatus.OK);
    }

}
