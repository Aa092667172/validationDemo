package com.example.validationdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultMessageOrder {
  @NotBlank(message = "{javax.validation.constraints.NotBlank.message}")
  private String name;

  @NotBlank(message = "{defaultMessageOrder.department.error}")
  private String department;
}
