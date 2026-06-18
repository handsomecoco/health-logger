# 智慧健康日誌與風險評估系統

## 專案說明
使用者每天記錄健康數據，系統透過決策樹自動評估健康風險等級（低/中/高）。

## 資料流向
Frontend (HTML/CSS/JS) → POST /health-logs → Spring Boot 後端 → SQLite 資料庫

## 決策樹邏輯（三層分支）
- 第一層：睡眠 < 6小時？
- 第二層：步數 < 3000？
- 第三層：心情 < 5分？
- 結果：低風險 / 中風險 / 高風險

## 技術堆疊
- 後端：Java + Spring Boot + Spring Data JPA
- 資料庫：SQLite
- 前端：HTML / CSS / JavaScript
- 部署：Railway

## API 端點
- GET /health-logs 取得所有紀錄
- POST /health-logs 新增紀錄（自動計算風險）
- PUT /health-logs/:id 修改紀錄
- DELETE /health-logs/:id 刪除紀錄
- GET /health-logs/risk 即時計算風險等級

## Live Demo
https://health-logger-production-3bbf.up.railway.app

## GitHub
https://github.com/handsomecoco/health-logger
