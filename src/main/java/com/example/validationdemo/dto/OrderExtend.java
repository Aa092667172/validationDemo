package com.example.validationdemo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderExtend extends Order {
  @NotNull(message = "訂單編號不得為空")
  private String orderId;
}
