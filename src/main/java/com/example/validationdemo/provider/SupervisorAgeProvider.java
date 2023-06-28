package com.example.validationdemo.provider;

import com.example.validationdemo.dto.Staff;
import com.example.validationdemo.dto.Supervisor;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 自定義驗證條件,依照主管年紀做員工驗證
 * 年紀介於20-60的主管 執行驗證
 */
public class SupervisorAgeProvider implements DefaultGroupSequenceProvider<Supervisor> {
  @Override
  public List<Class<?>> getValidationGroups(Supervisor supervisor) {
    final List<Class<?>> defaultGroupSequence = new ArrayList<>();
    defaultGroupSequence.add(Supervisor.class);
    Optional.ofNullable(supervisor)
        .map(Supervisor::getAge)
        .filter(value -> value >= 20 && value <= 60)
        .ifPresent(value -> defaultGroupSequence.add(Supervisor.StaffValidation.class));
    return defaultGroupSequence;
  }
}
