package com.example.validationdemo.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@EnableRetry
@Service
public class RetryService {
  @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 2000L, multiplier = 2))
  public String retry(String a) {
    System.out.println("retry");
    if ("fail".equals(a)) {
      throw new IllegalArgumentException("fail");
    }
		return "成功";
  }
  @Recover
  public String fail(String a,String b) {
    System.out.println(b);
    System.out.println(a);
    System.out.println("in recover");
    return "失敗";
  }

}
