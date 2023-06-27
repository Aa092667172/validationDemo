package com.example.validationdemo.provider;

import com.example.validationdemo.dto.Supervisor;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定義驗證條件,依照主管年紀做員工驗證
 * 年紀介於20-60的主管 執行驗證
 */
public class SupervisorAgeProvider implements DefaultGroupSequenceProvider<Supervisor> {

  @Override
  public List<Class<?>> getValidationGroups(Supervisor supervisor) {
    List<Class<?>> defaultGroupSequence = new ArrayList<>();
    defaultGroupSequence.add(Supervisor.class);
    if (supervisor != null) {
      if (supervisor.getAge() >= 20 || 60 <= supervisor.getAge()) {
        defaultGroupSequence.add(Supervisor.StaffValidation.class);
      }
    }
    return defaultGroupSequence;
  }
}