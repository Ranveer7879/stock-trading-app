package com.example.stock_trading_app.repository;

import com.example.stock_trading_app.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByName(String name);
}
