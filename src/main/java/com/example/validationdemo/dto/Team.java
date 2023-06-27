package com.example.validationdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {
  public interface Insert{}

  @NotNull(message = "組別姓名不得為空")
  private String name;
  @NotNull(message = "組別年紀不得為空",groups = {Insert.class})
  private Integer age;



}
