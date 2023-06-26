package com.example.validationdemo.controller;

import com.example.validationdemo.dto.DefaultMessageOrder;
import com.example.validationdemo.dto.Employee;
import com.example.validationdemo.dto.Result;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/myValidation")
public class MyController {
  @PostMapping
  public ResponseEntity<Object> insert(@Valid @RequestBody  Employee employee){
    return ResponseEntity.ok(Result.success(employee));
  }

  @PostMapping(value = "/i18n")
  public ResponseEntity<Object> insert(@Valid @RequestBody  DefaultMessageOrder order){
    return ResponseEntity.ok(Result.success(order));
  }
}
