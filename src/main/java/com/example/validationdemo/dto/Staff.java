package com.example.validationdemo.dto;

import com.example.validationdemo.provider.StaffProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@GroupSequenceProvider(StaffProvider.class)
public class Staff {
  public interface EnableValidation  {}
  public interface NotEnableValidation {}

  @NotNull(message = "員工狀態啟用中,地址為必填",groups = EnableValidation.class)
  private String address;
  @NotNull(message = "員工狀態啟用中,姓名為必填",groups = EnableValidation.class)
  private String name;
  @NotNull(message = "員工狀態關閉中,年紀為必填",groups = NotEnableValidation.class)
  private String age;
  @NotNull(message = "員工狀態關閉中,手機為必填",groups = NotEnableValidation.class)
  private String phone;

  @Pattern(message = "員工狀態格式錯誤",regexp = "^[YN]$")
  @NotNull(message = "員工狀態為必填")
  private String isEnable;


}
