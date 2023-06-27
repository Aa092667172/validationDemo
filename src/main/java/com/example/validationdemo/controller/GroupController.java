package com.example.validationdemo.controller;

import com.example.validationdemo.dto.Result;
import com.example.validationdemo.dto.Staff;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/staff")
public class GroupController {
  @PostMapping
  public ResponseEntity<Object> insert(@RequestBody
                                         @Valid Staff staff){
    return ResponseEntity.ok(Result.success(staff));
  }
}
