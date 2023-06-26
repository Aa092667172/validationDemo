package com.example.validationdemo.dto;

import com.example.validationdemo.annotation.Password;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Employee {
  private Long id;
  @NotBlank(message = "名稱不得為空白")
  private String name;
  @Password(message = "密碼格式錯誤")
  private String password;
}
