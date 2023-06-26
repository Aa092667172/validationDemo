package com.example.validationdemo.dto;

import lombok.Data;

@Data
public class Result<T> {
  private String code;
  private String message;
  private T data;

  private Result(T data){
    this.data = data;
  }

  private Result(){

  }


  /**
   * 成功回覆資料
   *
   */
  public static <T> Result<T> success(T data){
    Result<T> result = new Result<>(data);
    result.setCode("200");
    result.setMessage("成功");
    return result;
  }

  public static <T> Result<T> success(){
    Result<T> result = new Result<>();
    result.setCode("200");
    result.setMessage("成功");
    return result;
  }
}
