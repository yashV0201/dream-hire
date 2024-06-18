package com.dreamhire.job_profile.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@FeignClient("OPEN-AI")
public interface OpenAiInterface {
    @PostMapping("/hello")
    public ResponseEntity<String> getResponse(@RequestParam String prompt, @RequestParam String text);
}
