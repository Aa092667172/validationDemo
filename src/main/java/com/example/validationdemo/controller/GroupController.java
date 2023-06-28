package com.example.validationdemo.controller;

import com.example.validationdemo.dto.Result;
import com.example.validationdemo.dto.Staff;
import com.example.validationdemo.dto.Supervisor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/group")
public class GroupController {
  @PostMapping("/staff")
  public ResponseEntity<Object> insert(@RequestBody
                                         @Valid Staff staff){
    return ResponseEntity.ok(Result.success(staff));
  }

  @PostMapping("/supervisor")
  public ResponseEntity<Object> insert(@RequestBody
                                       @Valid Supervisor supervisor){
    return ResponseEntity.ok(Result.success(supervisor));
  }
}
