package com.example.validationdemo.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.math.BigDecimal;

@Data
public class Order {
  /**
   * insert驗證群組
   */
  public interface Insert {

  }

  /**
   * update驗證群組
   * 默認皆為Default群組
   */
  public interface Update extends Default {

  }
  @Pattern(regexp = "^[/d]{4}-[/d]{2}-[/d]{2}$",message ="日期需為yyyy-MM-dd")
  @NotBlank(message = "日期不得為空")
  private String date;

  @NotNull(message = "id不得為空",groups = {Update.class})
  private Long id;

  @NotBlank(message = "性別不得為空")
  @Pattern(message = "性別參數格式錯誤",regexp = "^[01]$")
  private String sex;

  @NotBlank(message = "姓名不得為空",groups = {Insert.class,Update.class})
  @Length(message = "名稱長度錯誤,請介於{min}到{max}之間",max = 7,min = 2,
      groups = {Insert.class,Update.class})
  private String name;

//  @Max(message = "年記不得高於{value}",value = 30)
//  @Min(message = "年記不得低於{value}",value = 18)
  @Range(min = 18,max = 30,message = "年紀請介於{min}至{max}之間")
  private Integer age;

  @DecimalMax(message = "金額不得高於{value}", value = "10.00")
  @DecimalMin(message = "金額不得低於{value}", value = "1.00")
  private BigDecimal price;
}
