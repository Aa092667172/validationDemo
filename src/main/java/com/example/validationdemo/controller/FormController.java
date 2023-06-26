package com.example.validationdemo.controller;

import com.example.validationdemo.dto.FormData;
import com.example.validationdemo.dto.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/form")
public class FormController {

  @PostMapping
  public ResponseEntity<Object> formInsert(@Validated(value = FormData.Insert.class) @ModelAttribute FormData from) {
    return ResponseEntity.ok(Result.success(from.getId()));
  }

  @PostMapping("/typeError")
  public ResponseEntity<Object> formTypeError(@RequestParam(value = "id") Integer id) {
    return ResponseEntity.ok(Result.success(id));
  }

}
