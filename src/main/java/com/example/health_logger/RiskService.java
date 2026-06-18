package com.example.health_logger;

import org.springframework.stereotype.Service;

@Service
public class RiskService {

    public String calculate(Double sleepHours, Integer steps, Integer moodScore) {
        // 第一層：判斷睡眠
        if (sleepHours < 6.0) {
            // 第二層：判斷步數
            if (steps < 3000) {
                return "高風險";  // 睡眠不足 + 步數少
            } else {
                // 第三層：判斷心情
                if (moodScore < 5) {
                    return "高風險";  // 睡眠不足 + 步數還行但心情差
                } else {
                    return "中風險";  // 睡眠不足但其他還好
                }
            }
        } else {
            // 睡眠充足，第二層：判斷步數
            if (steps < 3000) {
                // 第三層：判斷心情
                if (moodScore < 5) {
                    return "中風險";  // 步數少且心情差
                } else {
                    return "中風險";  // 步數少
                }
            } else {
                // 第三層：判斷心情
                if (moodScore < 5) {
                    return "中風險";  // 睡眠步數都好但心情差
                } else {
                    return "低風險";  // 三項都正常
                }
            }
        }
    }
}