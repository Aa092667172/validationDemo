package com.example.validationdemo.controller;

import com.example.validationdemo.dto.Result;
import com.example.validationdemo.dto.Supervisor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@RestController
@RequestMapping("/group")
@Validated
public class GroupController {
  @PostMapping
  public ResponseEntity<Object> insert(@RequestBody
                                         @Valid Supervisor supervisor){
    return ResponseEntity.ok(Result.success(supervisor));
  }
}
