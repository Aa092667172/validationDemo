package com.example.validationdemo.advice;

import com.example.validationdemo.exception.ProjectException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * http method不同
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<Object> httpRequestMethodNotSupportedExceptionHandler() {
    HashMap<String, Object> map = new HashMap<>();
    map.put("請求錯誤", "系統支援httpMethod不相同");
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(map);
  }

  /**
   *   json轉換格式錯誤
   *   Request Body
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
    log.info("進入httpMessageNotReadableExceptionHandler",e);
    Map<String, Object> map = new HashMap<>();
    //型別錯誤
    if (e.getCause() instanceof InvalidFormatException) {
      InvalidFormatException ife = (InvalidFormatException) e.getCause();
      map.put(ife.getPath().get(0).getFieldName(),
          String.format("格式錯誤,需為%s型別", ife.getTargetType().getSimpleName()));
      return ResponseEntity.badRequest().body(map);
      //格式錯誤 接陣列給object MismatchedInputException
    }
    map.put("格式錯誤", e.getMessage());
    return ResponseEntity.badRequest().body(map);
  }


  /**
   * 控制器中使用@Valid,@Validated + @RequestBody 注解方法参数，且参数驗證失敗
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    log.info("進入methodArgumentNotValidExceptionHandler");
    List<String> errorMessages = getErrorMessages(e.getBindingResult());
    HashMap<String, List<String>> errorMap = new HashMap<>();
    errorMap.put("格式錯誤",errorMessages);
    return ResponseEntity.badRequest()
        .body(errorMap);
  }

  /**
   * 數據綁定驗證不合規 参数绑定失败或出现错误
   * 表單數據綁定 @ModelAttribute
   */
  @ExceptionHandler(BindException.class)
  public ResponseEntity<Object> bindExceptionExceptionHandler(BindException e) {
    log.info("進入BindException");
    List<String> errorMessages = getErrorMessages(e.getBindingResult());
    HashMap<String, List<String>> errorMap = new HashMap<>();
    errorMap.put("格式錯誤",errorMessages);
    return ResponseEntity.badRequest()
        .body(errorMap);
  }

  /**
   * http參數 驗證參數不匹配
   *
   */
  @ExceptionHandler(TypeMismatchException.class)
  public ResponseEntity<Object> typeMismatchExceptionHandler(TypeMismatchException e) {
    log.info("進入typeMismatchExceptionHandler");
    Map<String, Object> map = new HashMap<>();
    map.put("格式錯誤",e.getMessage());
    return ResponseEntity.badRequest().body(map);
  }

  /**
   *
   * http參數 驗證參數類別不匹配缺失
   * 例如：@RequestParam 缺失
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Object> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
    log.info("進入missingServletRequestParameterException");
    Map<String, Object> map = new HashMap<>();
    map.put("格式缺失",e.getMessage());
    return ResponseEntity.badRequest().body(map);
  }

  /**
   * validation驗證不通過
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException e) {
    log.info("進入constraintViolationExceptionHandler");
    List<String> errorMessages = e.getConstraintViolations()
        .stream()
        .map(filed -> filed.getPropertyPath() + ":" + filed.getMessage())
        .collect(Collectors.toList());
    HashMap<String, List<String>> errorMap = new HashMap<>();
    errorMap.put("格式錯誤",errorMessages);
    return ResponseEntity.badRequest()
        .body(errorMap);
  }

  @ExceptionHandler(ProjectException.class)
  public ResponseEntity<Object> projectExceptionHandler(ProjectException e) {
    HashMap<String, Object> map = new HashMap<>();
    map.put("請求錯誤",e.getMessage());
    map.put("code", e.getCode());
    return ResponseEntity.badRequest().body(map);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> exceptionHandler(Exception e) {
    log.error("系統錯誤",e);
    HashMap<String, Object> map = new HashMap<>();
    map.put("系統錯誤", "請與系統管理員聯絡");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
  }

  /**
   * 取得錯誤訊息
   * @param e
   * @return
   */
  private static List<String> getErrorMessages(BindingResult e) {
    return e.getFieldErrors()
        .stream()
        .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage())
        .collect(Collectors.toList());
  }
}
