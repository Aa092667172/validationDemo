package com.example.validationdemo.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
public class Book {
  @NotEmpty(message = "書名不得為空")
  private String name;
  @Max(message = "書本金額不得高於{value}",value = 300)
  @NotNull(message = "金額不得為null")
  private Integer price;

  /**
   *
   */
  public static void main(String[] args) {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Book book = Book.builder()
        .price(600)
        .build();

    Set<ConstraintViolation<Book>> violations = validator.validate(book);
    for (ConstraintViolation<Book> violation : violations) {
      System.out.println(violation.getMessage());
    }

  }

}
