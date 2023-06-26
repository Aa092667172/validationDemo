package com.example.validationdemo.annotation;

import com.example.validationdemo.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { PasswordValidator.class })
public @interface Password {
  int upperLetter() default 1;
  int lowerLetter() default 1;
  int number() default 1;
  String message();
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
