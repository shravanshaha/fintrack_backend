# FinTrack Backend

Backend API for the FinTrack Personal Finance Management Application built using Java Servlets, JDBC, PostgreSQL, and Docker.

## Features

- User Registration
- User Authentication
- Category Management
- Transaction Management
- Transaction Search
- Date Range Filtering
- Dashboard Analytics
- Category-wise Expense Reports
- REST-style API Endpoints
- PostgreSQL Integration
- Docker Support
- Cloud Deployment Ready

## Tech Stack

### Backend

- Java 21
- Jakarta Servlets
- JDBC
- Maven
- Docker

### Database

- PostgreSQL
- Supabase

### Deployment

- Render
- Docker Container

## API Modules

### Authentication

- Register User
- Login User
- Profile Retrieval

### Categories

- Create Category
- List Categories
- Delete Category

### Transactions

- Add Transaction
- View Transactions
- Search Transactions
- Filter by Date Range

### Dashboard

- Income Summary
- Expense Summary
- Balance Summary
- Expense by Category Analytics

## Project Structure

src/

├── main/

│ ├── java/

│ │ ├── dao/

│ │ ├── db/

│ │ ├── model/

│ │ └── servlet/

│ └── webapp/

├── Dockerfile

├── pom.xml

└── README.md

## Deployment Architecture

React Frontend (Vercel)
↓
Java Servlets Backend (Render)
↓
PostgreSQL Database (Supabase)

## Related Repositories

Frontend Repository:
https://github.com/shravanshaha/fintrack-frontend.git

Live Application:
https://fintrack-frontend-lemon.vercel.app/

Backend API:
https://fintrack-backend-c16m.onrender.com

## Author

Shravan Shaha
