package com.it.survey.demo.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.database.Query;
import com.it.survey.demo.dto.SurveyDTO;
import com.it.survey.demo.dto.SurveyStadisticsDTO;
import com.it.survey.demo.firebase.FirebaseInitializer;
import com.it.survey.demo.service.ISurveyService;

@Service
public class SurveyServiceImplement implements ISurveyService {

	@Autowired
	private FirebaseInitializer firebase;

	@Override
	public List<SurveyDTO> list() {
		List<SurveyDTO> response = new ArrayList<>();
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();
		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				surveyProcess(response, doc);
			}
			return response;
		} catch (Exception e) {
			return response;
		}
	}

	private void surveyProcess(List<SurveyDTO> response, DocumentSnapshot doc) {
		SurveyDTO survey;
		survey = doc.toObject(SurveyDTO.class);
		survey.setId(doc.getId());
		response.add(survey);
	}

	@Override
	public List<SurveyStadisticsDTO> surveyStadistics() throws InterruptedException {
		List<SurveyDTO> response = new ArrayList<>();
		List<SurveyStadisticsDTO> stadistics = new ArrayList<>();
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		long sum = 0;

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				surveyProcess(response, doc);
			}

			Map<String, Long> survey = response.parallelStream()
					.collect(Collectors.groupingByConcurrent(SurveyDTO::getMusic, Collectors.counting())).entrySet()
					.stream().sorted(Entry.comparingByValue())
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

			for (Entry<String, Long> entry : survey.entrySet()) {
				sum += entry.getValue();
			}

			for (Map.Entry<String, Long> entry : survey.entrySet()) {
				SurveyStadisticsDTO s = new SurveyStadisticsDTO();

				s.setMusic(entry.getKey());
				s.setTotal(entry.getValue());
				s.setUniverse(sum);
				stadistics.add(s);
			}

		} catch (ExecutionException e3) {
			e3.printStackTrace();
		}
		return stadistics;
	}

	@Override
	public Boolean add(SurveyDTO survey) {
		Map<String, Object> docData = getDocData(survey);

		ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);

		try {

			if (null != writeResultApiFuture.get()) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

	private CollectionReference getCollection() {
		return firebase.getFirestore().collection("survey");
	}

	private Map<String, Object> getDocData(SurveyDTO survey) {
		Map<String, Object> docData = new HashMap<>();
		docData.put("email", survey.getEmail());
		docData.put("music", survey.getMusic());
		return docData;
	}

}
