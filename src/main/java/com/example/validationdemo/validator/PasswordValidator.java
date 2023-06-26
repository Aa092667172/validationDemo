package com.example.validationdemo.validator;

import com.example.validationdemo.annotation.Password;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自製密碼驗證Validator
 */
@Slf4j
public class PasswordValidator implements ConstraintValidator<Password,String> {
  private Password password;

  @Override
  public void initialize(Password constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.password = constraintAnnotation;
  }

  /**
   * true代表驗證通過
   * @param value object to validate
   * @param context context in which the constraint is evaluated
   *
   * @return
   */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    int numberCount = 0;
    int lowerCount = 0;
    int upperCount = 0;
    for (int i = 0; i < value.length(); i++) {
      char ch = value.charAt(i);
      if (Character.isDigit(ch)) {
        numberCount++;
      } else if (Character.isUpperCase(ch)) {
        upperCount++;
      } else if (Character.isLowerCase(ch)) {
        lowerCount++;
      }
    }

    String defaultConstraintMessageTemplate = context.getDefaultConstraintMessageTemplate();
    log.info(defaultConstraintMessageTemplate);
    boolean isError = true;

    if(numberCount < password.number()){
      ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder =
          context.buildConstraintViolationWithTemplate(String.format("需包含%d個數字",
              password.number()));
      constraintViolationBuilder.addConstraintViolation();
      isError = false;
    }
    if(lowerCount < password.lowerLetter()){
      ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder =
          context.buildConstraintViolationWithTemplate(String.format("需包含%d個小寫字母",
              password.lowerLetter()));
      constraintViolationBuilder.addConstraintViolation();
      isError = false;
    }
    if(upperCount < password.upperLetter()){
      ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder =
          context.buildConstraintViolationWithTemplate(String.format("需包含%d個大寫字母",
              password.upperLetter()));
      constraintViolationBuilder.addConstraintViolation();
      isError = false;
    }
    return isError;
  }
}
