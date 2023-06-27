package com.example.validationdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormData {

  public interface Insert extends Default {}

  @Pattern(regexp = "^[01]$", message = "id需為0或1")
  @NotNull(message = "表單id不得為空")
  private String id;

  @NotNull(message = "名稱不得為空",groups = Insert.class)
  private String name;
}
