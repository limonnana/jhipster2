package com.limonnana.repository;

import com.limonnana.domain.MonthList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthRepository extends JpaRepository<MonthList, Long> {
}
