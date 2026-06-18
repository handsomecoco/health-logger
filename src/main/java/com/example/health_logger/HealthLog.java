package com.example.health_logger;

import jakarta.persistence.*;

@Entity
@Table(name = "health_logs")
public class HealthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logDate;
    private Double sleepHours;
    private Integer steps;
    private Integer moodScore;
    private String riskLevel;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLogDate() { return logDate; }
    public void setLogDate(String logDate) { this.logDate = logDate; }

    public Double getSleepHours() { return sleepHours; }
    public void setSleepHours(Double sleepHours) { this.sleepHours = sleepHours; }

    public Integer getSteps() { return steps; }
    public void setSteps(Integer steps) { this.steps = steps; }

    public Integer getMoodScore() { return moodScore; }
    public void setMoodScore(Integer moodScore) { this.moodScore = moodScore; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
}