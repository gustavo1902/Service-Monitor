package com.projeto.service_monitor.controller;

import com.projeto.service_monitor.model.MonitorTarget;
import com.projeto.service_monitor.repository.MonitorTargetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monitors")
public class MonitorTargetController {

    private final MonitorTargetRepository repository;

    public MonitorTargetController(MonitorTargetRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<MonitorTarget> create(@RequestBody MonitorTarget newTarget) {
        MonitorTarget savedTarget = repository.save(newTarget);
        return ResponseEntity.ok(savedTarget);
    }

    @GetMapping
    public ResponseEntity<List<MonitorTarget>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}