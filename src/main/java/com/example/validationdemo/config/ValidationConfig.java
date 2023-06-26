package com.example.validationdemo.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.Resource;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

@Configuration
public class ValidationConfig {
  @Resource
  private MessageSource messageSource;

  /**
   * Validation message i18n
   * @return Validator
   */
  @Bean
  public Validator i18nValidator() {
   LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
    validator.setValidationMessageSource(this.messageSource);
    return validator;
  }

}
