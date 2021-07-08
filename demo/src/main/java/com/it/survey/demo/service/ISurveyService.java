package com.it.survey.demo.service;

import com.it.survey.demo.dto.SurveyDTO;
import com.it.survey.demo.dto.SurveyStadisticsDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface ISurveyService {
	

    List<SurveyDTO> list();

    Boolean add(SurveyDTO survey);

	List<SurveyStadisticsDTO> surveyStadistics() throws ExecutionException, InterruptedException;

}
