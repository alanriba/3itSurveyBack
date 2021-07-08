package com.it.survey.demo;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.it.survey.demo.dto.SurveyDTO;
import com.it.survey.demo.service.imp.SurveyServiceImplement;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		SurveyDTO sur = new SurveyDTO();
		SurveyServiceImplement s = new SurveyServiceImplement();
		
		sur.setEmail("123@123.cl");
		sur.setMusic("Rock");
		assert(s.add(sur));
		
	}

}
