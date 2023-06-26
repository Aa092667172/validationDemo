package com.example.validationdemo.controller;

import com.example.validationdemo.dto.Auth;
import com.example.validationdemo.dto.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
  @PostMapping
  public ResponseEntity<Object> insert(@Valid @RequestBody Auth auth){
    return ResponseEntity.ok(Result.success(auth));
  }
}
