package com.example.validationdemo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Book {
  @NotEmpty(message = "書名不得為空")
  private String name;
  @Max(message = "書本金額不得高於{value}",value = 300)
  @NotNull(message = "金額不得為null")
  private Integer price;
}
