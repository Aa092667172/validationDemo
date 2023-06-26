package com.example.validationdemo.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
@RequiredArgsConstructor
public class ValidationConfig {
  private final MessageSource messageSource;

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

  /**
   * 不需i18n 可使用
   *
   */
//  @Bean
//  public Validator validator() {
//    ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
//        .configure()
//        //使一有錯誤立即返回
////        .failFast(true)
//        .buildValidatorFactory();
//    return validatorFactory.getValidator();
//  }

}
