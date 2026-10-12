package com.example.eventregistration.controller;

import com.example.eventregistration.dto.EventRegistrationRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EventRegistrationController {

    @PostMapping("/events/register")
    public ResponseEntity<Map<String, Object>> registerEvent(@Valid @RequestBody EventRegistrationRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Event registration successful");
        response.put("fullName", request.getFullName());
        response.put("email", request.getEmail());
        response.put("eventDate", request.getEventDate());
        response.put("registeredAt", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}