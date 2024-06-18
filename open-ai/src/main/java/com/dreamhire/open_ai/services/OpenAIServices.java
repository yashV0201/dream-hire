package com.dreamhire.open_ai.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Log
@RequiredArgsConstructor
public class OpenAIServices {
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public String processTextWithOpenAI(String prompt,String text) throws IOException {
        String url = "https://api.openai.com/v1/chat/completions";
        // Construct the JSON body
        Map<String, Object> messageContent = new HashMap<>();
        messageContent.put("role", "user");
        messageContent.put("content", text);

        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content",prompt );

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4");
        requestBody.put("messages", new Object[]{systemMessage, messageContent});
        requestBody.put("temperature",0);

        // Convert request body to JSON
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);

        // Log the JSON request body for debugging
        System.out.println("JSON Request Body: " + jsonRequestBody);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(jsonRequestBody, headers);

        // Send request and get response
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            // Parse the response
            JsonNode responseJson = objectMapper.readTree(response.getBody());
            return responseJson.path("choices").get(0).path("message").path("content").asText();
        } catch (HttpClientErrorException e) {
            // Log the response body for debugging
            System.out.println("Error Response Body: " + e.getResponseBodyAsString());
            throw new IOException("Error processing file: " + e.getMessage());
        }
    }


}
