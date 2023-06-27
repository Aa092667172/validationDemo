package com.example.validationdemo.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 手動分組校驗
 */
@Data
@Builder
public class Dept {

  public interface ValidationPhone{}
  public interface ValidationName{}

  //控制驗證順序
  @GroupSequence({ValidationName.class,ValidationPhone.class})
  public interface Group {}

  @NotNull(message = "年齡不得為空",groups = ValidationName.class)
  private String age;

  @NotNull(message = "部門名稱不得為空",groups = ValidationName.class)
  private String name;

  @NotNull(message = "手機為必填",groups = ValidationPhone.class)
  private String phone;

  @NotNull(message = "地址")
  private String address;

  @Valid
  private Team team;

  public static void main(String[] args) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    Dept test = Dept.builder()
//        .name("test")
        .team(
            Team.builder()
                .name("123")
                .build()
        )
        .build();
    //需放入介面
//    Set<ConstraintViolation<Dept>> violations = validator.validate(test, Default.class ,Team.Insert.class);
    Set<ConstraintViolation<Dept>> violations = validator.validate(test,Group.class);
    for (ConstraintViolation<Dept> violation : violations) {
      System.out.println(violation.getMessage());
    }
  }
}
