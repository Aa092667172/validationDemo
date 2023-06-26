package com.example.validationdemo.exception;

import lombok.Getter;

/**
 * 專案自製例外
 */
@Getter
public class ProjectException extends RuntimeException {
  private final String code;

  public ProjectException(String msg,String code){
    super(msg);
    this.code = code;
  }
}
