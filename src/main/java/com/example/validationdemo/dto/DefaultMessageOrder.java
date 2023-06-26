package com.example.validationdemo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class DefaultMessageOrder {
  @NotBlank(message = "{javax.validation.constraints.NotBlank.message}")
  private String name;

  @NotBlank(message = "{defaultMessageOrder.department.error}")
  private String department;
}
