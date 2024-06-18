package com.dreamhire.open_ai.controller;

import com.dreamhire.open_ai.services.OpenAIServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Log
@RestController
@RequiredArgsConstructor
public class Controller {

    private final OpenAIServices openAIServices;

    @PostMapping("/hello")
    public ResponseEntity<String> getResponse(@RequestParam String prompt, @RequestParam String text) throws IOException {
        String response = openAIServices.processTextWithOpenAI(prompt,text);
        return ResponseEntity.ok(response);
    }

}
