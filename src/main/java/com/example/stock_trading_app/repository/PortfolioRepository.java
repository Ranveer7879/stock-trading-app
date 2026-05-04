package com.example.stock_trading_app.repository;

import com.example.stock_trading_app.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Portfolio findByStockName(String stockName);
}