package com.example.health_logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/health-logs")
@CrossOrigin(origins = "*")
public class HealthLogController {

    @Autowired private HealthLogRepository repo;
    @Autowired private RiskService riskService;

    // ★ 新增這個，啟動時自動補算所有 riskLevel
    @jakarta.annotation.PostConstruct
    public void initRiskLevels() {
        List<HealthLog> logs = repo.findAll();
        for (HealthLog log : logs) {
            if (log.getRiskLevel() == null) {
                log.setRiskLevel(riskService.calculate(
                    log.getSleepHours(), log.getSteps(), log.getMoodScore()
                ));
            }
        }
        repo.saveAll(logs);
    }

    @GetMapping
    public List<HealthLog> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public HealthLog create(@RequestBody HealthLog log) {
        String risk = riskService.calculate(
            log.getSleepHours(), log.getSteps(), log.getMoodScore()
        );
        log.setRiskLevel(risk);
        return repo.save(log);
    }

    @PutMapping("/{id}")
    public HealthLog update(@PathVariable Long id, @RequestBody HealthLog updated) {
        HealthLog log = repo.findById(id).orElseThrow();
        log.setLogDate(updated.getLogDate());
        log.setSleepHours(updated.getSleepHours());
        log.setSteps(updated.getSteps());
        log.setMoodScore(updated.getMoodScore());
        log.setRiskLevel(riskService.calculate(
            updated.getSleepHours(), updated.getSteps(), updated.getMoodScore()
        ));
        return repo.save(log);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @GetMapping("/risk")
    public Map<String, String> getRisk(
        @RequestParam Double sleep,
        @RequestParam Integer steps,
        @RequestParam Integer mood
    ) {
        String level = riskService.calculate(sleep, steps, mood);
        return Map.of("riskLevel", level);
    }
}