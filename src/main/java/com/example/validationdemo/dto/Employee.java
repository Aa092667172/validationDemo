package com.example.validationdemo.dto;

import com.example.validationdemo.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
  private Long id;
  @NotBlank(message = "名稱不得為空白")
  private String name;
  @Password(message = "密碼格式錯誤",lowerLetter = 2)
  private String password;
}
