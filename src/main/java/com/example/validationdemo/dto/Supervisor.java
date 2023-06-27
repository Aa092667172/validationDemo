package com.example.validationdemo.dto;

import com.example.validationdemo.provider.SupervisorAgeProvider;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 動態分組校驗
 * 依照主管年紀去驗證底下員工
 */
@Data
@Builder
@GroupSequenceProvider(SupervisorAgeProvider.class)
public class Supervisor {
  private String name;
  @NotNull(message = "手機不得為空")
  private String phone;
  @NotNull(message = "年紀不能得空")
  private Integer age;
  @Valid
  @NotNull(message = "職員不得為空",groups = StaffValidation.class)
  private Staff staff;
  public interface StaffValidation {}

  public static void main(String[] args) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Supervisor supervisor = Supervisor.builder()
        .name("驗證群組b")
        .phone("0919332788")
        .age(20)
        .staff(
            Staff.builder()
                .isEnable("N")
                .name("驗證b")
                .build()
        )
        .build();

    Set<ConstraintViolation<Supervisor>> violations = validator.validate(supervisor);
    for (ConstraintViolation<Supervisor> violation : violations) {
      System.out.println(violation.getMessage());
    }
  }
}
