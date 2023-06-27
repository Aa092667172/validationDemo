package com.example.validationdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
  @NotNull(message = "作者姓名不得為空")
  private String name;
  @Pattern(regexp = "^[09][/d]{8}$",message = "手機號碼需為09開頭並且長度為10")
  @NotNull(message = "手機號碼不得為空")
  private String phoneNumber;
  @Valid
  @Size(message = "書本集合1需介於{min}與{max}之間",min = 1,max = 3)
  @NotEmpty(message = "bookList不得為null")
  private List<Book> bookList;
  @Valid
  @Size(message = "書本集合2長度不得高於{max}",max = 3)
  private List<Book> bookList2;
}
