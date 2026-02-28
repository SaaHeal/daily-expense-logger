# Daily Expense Logger: Nebula Dark Edition

A premium, high-performance financial ledger designed with a modern **Nebula Dark** aesthetic and full support for Indian currency (₹). Built with Spring Boot and React.

![Nebula Dark Dashboard Screen](backend/src/main/resources/static/index.css) <!-- Note: This is a placeholder for the aesthetic style -->

## ✨ Features

- **Nebula Dark Design**: A deep, immersive dark theme with subtle glassmorphism and soft glow effects.
- **Indian Rupee (₹) Support**: Native currency formatting and symbols (en-IN) throughout.
- **Real-time Analytics**: Instantly view spending totals for Today, This Week, This Month, and This Year.
- **Robust Validation**: Backend-level validation to prevent malformed or empty data entries.
- **Integrated Stack**: The React frontend is served directly by the Spring Boot backend for seamless deployment and zero CORS issues.
- **Lucide Icons**: Context-aware icons for different spending categories (Food, Petrol, Rent, etc.).

## 🚀 Tech Stack

- **Backend**: Java 21, Spring Boot 3.4.3, Spring Data JPA
- **Database**: H2 (In-Memory) for fast, zero-config performance.
- **Frontend**: React 18, CSS3 (Custom Properties), Lucide Icons
- **Build Tool**: Maven

## 🛠️ Getting Started

### Prerequisites

- **Java 21** or higher.
- **Maven** 3.8+.

### Running Locally

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/daily-expense-logger.git
   cd daily-expense-logger/backend
   ```

2. **Run the Spring Boot application**:
   ```bash
   mvn spring-boot:run
   ```

3. **Access the application**:
   Open [http://localhost:8080](http://localhost:8080) in your browser.

## 📖 API Documentation

The following REST endpoints are available:

- `GET /api/expenses`: Retrieve all transactions (chronological).
- `GET /api/expenses/summary`: Retrieve spending totals for various time periods.
- `POST /api/expenses`: Record a new transaction.
  - Body: `{"amount": 500.0, "category": "Food", "description": "Dinner"}`

## 🛡️ Validation & Resilience

- **Null Checks**: The system prevents recording transactions without an amount.
- **Graceful Failures**: The frontend handles server interruptions with clear user alerts.
- **In-Memory Store**: Data is cleared on server restart (ideal for privacy-first ephemeral logging or development).

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.
