package com.example.validationdemo.service;

import com.example.validationdemo.dto.Order;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
@Service
public class OrderService {
  public void valid(@Valid Order order){
    //do something
  }

  /**
   * controller外的驗證需要分群都要加在方法上
   * 並且需要在類別上加入@Validated 由spring做代理驗證
   */
  @Validated(Order.Insert.class)
  public void validaGroup(@Valid Order order){
    //do something
  }
}
