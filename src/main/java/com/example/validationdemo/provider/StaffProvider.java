package com.example.validationdemo.provider;

import com.example.validationdemo.dto.Staff;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 依照員工狀態做驗證
 */
public class StaffProvider implements DefaultGroupSequenceProvider<Staff> {
  @Override
  public List<Class<?>> getValidationGroups(Staff staff) {
    List<Class<?>> defaultGroupSequence = new ArrayList<>();
    defaultGroupSequence.add(Staff.class);
    if (staff != null) {
      if (staff.getIsEnable().equals("Y")) {
        defaultGroupSequence.add(Staff.EnableValidation.class);
      } else if (staff.getIsEnable().equals("N")) {
        defaultGroupSequence.add(Staff.NotEnableValidation.class);
      }
    }
    return defaultGroupSequence;
  }
}
