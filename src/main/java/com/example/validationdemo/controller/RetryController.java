package com.example.validationdemo.controller;

import com.example.validationdemo.service.RetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retry")
@RequiredArgsConstructor
public class RetryController {
  private final RetryService retryService;
  @GetMapping("/{action}")
  public ResponseEntity<Object> retry(@PathVariable  String action) {
    return ResponseEntity.ok(retryService.retry(action));
  }
}
