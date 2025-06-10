package com.projeto.service_monitor.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "monitor_targets")
@Data // Lombok
public class MonitorTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; 

    @Column(nullable = false, unique = true)
    private String url; 

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNKNOWN;

    private LocalDateTime lastCheck;

    private String lastErrorMessage;

    public enum Status {
        UP, DOWN, UNKNOWN
    }
}