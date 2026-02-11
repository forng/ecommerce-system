# 🛒 Java MVC 電商系統（Swing + MySQL）

## 📌 專案簡介

本專案為 Java MVC 架構電商系統，使用：

- Java (Swing / JFrame)
- MySQL
- Maven Project
- DAO Pattern
- MVC 分層設計

系統支援：

- 會員註冊 / 登入
- 商品選購
- 購物車
- 訂單建立
- 歷史訂單查詢
- 管理者訂單管理

---

# 🏗 系統架構

```
controller/
    users/
    orders/
    admin/

service/
    userservice/
    orderservice/

dao/
    users/
    orders/
    orderItems/

model/
    Users
    CartInput
    Orders
    OrderItems

vo/
    OrderDetailVO

util/
    Tool (DB連線 / 序列化)
```

---

# 🧠 設計重點

## ❌ 不使用 Trigger

本專案：

- ❌ 不使用 MySQL Trigger
- ❌ 不依賴資料庫自動編號邏輯

## ✅ 所有編號由 Java DAO 控制

| 類型 | 編號格式 | 產生方式 |
|------|----------|----------|
| user_no | U001 | 依 id 計算 |
| order_no | O001 | 依 id 計算 |

---

# 🔢 編號產生邏輯（DAO 控制）

## 流程

1. INSERT（不給 user_no / order_no）
2. 取得 AUTO_INCREMENT id
3. 使用 `String.format()` 產生編號
4. UPDATE 回資料庫

### user_no 產生方式

```java
String userNo = String.format("U%03d", id);
```

### order_no 產生方式

```java
String orderNo = String.format("O%03d", id);
```

---

# 🗄 資料庫設計

## users

| 欄位 | 說明 |
|------|------|
| id | AUTO_INCREMENT |
| user_no | U001 |
| username | 帳號 |
| password_hash | 密碼 |
| name | 姓名 |
| city | 城市 |
| address | 地址 |
| phone | 電話 |
| created_at | 註冊時間 |

---

## orders

| 欄位 | 說明 |
|------|------|
| id | AUTO_INCREMENT |
| order_no | O001 |
| user_no | 外鍵 |
| created_at | 建立時間 |

---

## order_items

| 欄位 | 說明 |
|------|------|
| id | AUTO_INCREMENT |
| order_no | 外鍵 |
| product_no | 商品編號 |
| amounts | 購買數量 |

---

# 🛍 前台功能

## 1️⃣ 註冊

- 檢查帳號是否存在
- 若存在 → 顯示錯誤頁
- 若不存在 → 建立新會員
- 自動產生 user_no

---

## 2️⃣ 登入

- 驗證 username + password
- 成功 → LoginSuccessUI
- 失敗 → LoginFailUI

---

## 3️⃣ 商品頁

- 可輸入數量（預設 0）
- 支援優惠條件：

| 條件 | 優惠 |
|------|------|
| 滿 1000 | 打 9 折 |
| 滿 500 | 免運 |
| 未滿 500 | 運費 60 |

---

## 4️⃣ 購物車

顯示：

- 商品明細
- 小計
- 折扣
- 運費
- 總金額

---

## 5️⃣ 訂單確認

- 顯示當前訂單
- 查詢歷史訂單
- 顯示：
  - 訂單編號
  - 商品明細
  - 總金額
  - 建立時間

---

# 👨‍💼 管理員模式

## 登入帳號

```
帳號：AAA
密碼：AAA1234
```

---

## 管理功能

- 查詢所有訂單
- 依客戶編號查詢
- 依產品編號查詢
- 依金額區間查詢
- 顯示加總總金額
- 清空查詢欄位

---

# 🧮 商業邏輯分層

| 功能 | 所在層 |
|------|--------|
| 計算折扣 | Service |
| 訂單建立 | Service |
| SQL 操作 | DAO |
| UI 顯示 | Controller |
| 編號生成 | DAO |

---

# 🔐 安全設計

- 使用 PreparedStatement
- 不使用字串拼接 SQL
- DAO 控制資料存取
- UI 不做計算邏輯

---

# 🚀 執行方式

1. 匯入 MySQL schema
2. 確認：
   - `users.user_no` 允許 NULL
   - `orders.order_no` 允許 NULL
3. 執行 LoginUI
4. 測試流程：

```
註冊 → 登入 → 選購 → 結帳 → 訂單 → 管理員
```

---

# ⚠ 注意事項

- 本系統不使用 trigger
- id 必須為 AUTO_INCREMENT
- 編號由 DAO 計算
- 匯出匯入資料庫可直接使用

---

# 📈 系統特色

- 純 Java MVC 架構
- 無框架依賴
- 無 Trigger 依賴
- 可跨主機執行
- 架構清晰
- 商業邏輯分層明確

---

# 👨‍💻 作者

Java MVC 電商練習專案  
Swing + MySQL + DAO Pattern

