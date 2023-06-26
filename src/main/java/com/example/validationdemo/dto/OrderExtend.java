package com.example.validationdemo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderExtend extends Order {
  @NotNull(message = "orderId不得為null")
  private String orderId;
}
