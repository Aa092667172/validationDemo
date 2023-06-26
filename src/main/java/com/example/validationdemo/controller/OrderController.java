package com.example.validationdemo.controller;

import com.example.validationdemo.dto.Order;
import com.example.validationdemo.dto.OrderExtend;
import com.example.validationdemo.dto.Result;
import com.example.validationdemo.exception.ProjectException;
import com.example.validationdemo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
@Validated
@RequiredArgsConstructor
@Slf4j
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Object> insertOrder(@Validated(Order.Insert.class)
                                                          @RequestBody Order order){
    return ResponseEntity.ok(Result.success(order));
  }

  @PostMapping("/extends")
  public ResponseEntity<Object> insertOrder(@Validated(Order.Update.class)
                                                          @RequestBody OrderExtend order){
    return ResponseEntity.ok(Result.success(order));
  }

  @PutMapping
  public ResponseEntity<Object> editOrder(@Validated(Order.Update.class) @RequestBody Order order) {
    return ResponseEntity.ok(Result.success(order));
  }

  @PatchMapping
  public ResponseEntity<Object> defaultValid(@Valid @RequestBody Order order){
    return ResponseEntity.ok(Result.success(order));
  }

  /**
   * 該驗證需有效果,需要再類別上加入@Validated才會生效
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable("id")
                                          @Pattern(regexp = "^[/d]$", message = "需要是數字") String id) {
    return ResponseEntity.ok(Result.success(id));
  }

  @PostMapping("/valid")
  public ResponseEntity<Object> valid(@RequestBody Order order){
    orderService.valid(order);
    return ResponseEntity.ok(Result.success());
  }
  @PostMapping("/validGroup")
  public ResponseEntity<Object> validGroup(@RequestBody Order order){
    orderService.validaGroup(order);
    return ResponseEntity.ok(Result.success());
  }

  /**
   * 假設為非java bean 需要實施驗證效果 需要在類別上加入@Validated才會生效
   * 分群則需要再加上method上
   */
  @PostMapping("/list")
//  @Validated(Order.Insert.class)
  public ResponseEntity<Object> list(@Valid @RequestBody List<Order> orderList){
    return  ResponseEntity.ok(Result.success(orderList));
  }


  @PostMapping("/validationStringList")
  public ResponseEntity<Object> validationStringList(
      @RequestBody List<@Pattern(regexp = "^[01]$",message = "集合中的字串需為0或1數字") String>  orderList){
    return  ResponseEntity.ok(Result.success(orderList));
  }

  /**
   * 當傳入BindingResult 則將例外拋出的控制權 交給操作者
   * 但假設使用@Validated並且類別上方有加入@Validated,還是可以取得控制權
   */
  @PostMapping("/bindError")
  public ResponseEntity<Object> bindError(@Validated @RequestBody Order order, BindingResult bindingResult
                                          ){
    log.info("bindingResult.getAllErrors()",bindingResult.getAllErrors());
    log.info("bindingResult.hasErrors()",bindingResult.hasErrors());
    log.info("bindingResult.getErrorCount()",bindingResult.getErrorCount());

    if (bindingResult.hasErrors()) {
      //do something and then
      throw new ProjectException(bindingResult.getAllErrors()
          .stream().map(FieldError.class::cast)
          .map(FieldError::getDefaultMessage)
          .collect(Collectors.joining(",")),"400");
    }

    return  ResponseEntity.ok(Result.success(order));
  }

}
