package com.chatroom.chatapp.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SentimentService {

		private final RestTemplate restTemplate = new RestTemplate();

    public String analyzeSentiment(String text) {

    try 
    {
        String url = "http://127.0.0.1:5000/emotion_api";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("text", text);   // 🔥 MUST BE "content"

        HttpEntity<Map<String, String>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(url, request, Map.class);

        return response.getBody().get("emotion").toString();
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    	return "neutral";
    	}
    }
}
