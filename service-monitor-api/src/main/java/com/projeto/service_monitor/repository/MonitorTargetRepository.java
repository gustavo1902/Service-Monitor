package com.projeto.service_monitor.repository;

import com.projeto.service_monitor.model.MonitorTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorTargetRepository extends JpaRepository<MonitorTarget, Long> {
}