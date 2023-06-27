package com.example.validationdemo.provider;

import com.example.validationdemo.dto.Staff;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 依照員工狀態做驗證
 */
public class StaffProvider implements DefaultGroupSequenceProvider<Staff> {
  @Override
  public List<Class<?>> getValidationGroups(Staff staff) {
    List<Class<?>> defaultGroupSequence = new ArrayList<>();
    defaultGroupSequence.add(Staff.class);
    Optional.ofNullable(staff)
        .map(Staff::getIsEnable)
        .ifPresent(value->{
          if (staff.getIsEnable().equals("Y")) {
            defaultGroupSequence.add(Staff.EnableValidation.class);
          } else if (staff.getIsEnable().equals("N")) {
            defaultGroupSequence.add(Staff.NotEnableValidation.class);
          }
        });
    return defaultGroupSequence;
  }
}
