package com.example.stock_trading_app.service;


import com.example.stock_trading_app.model.Portfolio;
import com.example.stock_trading_app.model.Stock;
import com.example.stock_trading_app.repository.PortfolioRepository;
import com.example.stock_trading_app.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradingService {

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private PortfolioRepository portfolioRepo;

    private double balance = 100000;

    public Stock addStock(Stock stock){
        return stockRepo.save(stock);
    }

    // BUY STOCK
    public String buy(String name, int qty) {

        Stock stock = stockRepo.findByName(name);

        if (stock == null) {
            return "Stock not found";
        }

        double cost = stock.getPrice() * qty;

        if (cost > balance) {
            return "Insufficient balance";
        }

        balance -= cost;

        Portfolio p = portfolioRepo.findByStockName(name);

        if (p == null) {
            p = new Portfolio();
            p.setStockName(name);
            p.setQuantity(qty);
        } else {
            p.setQuantity(p.getQuantity() + qty);
        }

        portfolioRepo.save(p);

        return "Stock bought successfully";
    }

    // SELL STOCK
    public String sell(String name, int qty) {

        Portfolio p = portfolioRepo.findByStockName(name);

        if (p == null || p.getQuantity() < qty) {
            return "Not enough stock";
        }

        Stock stock = stockRepo.findByName(name);

        if (stock == null) {
            return "Stock not found";
        }

        balance += stock.getPrice() * qty;

        p.setQuantity(p.getQuantity() - qty);

        if (p.getQuantity() == 0) {
            portfolioRepo.delete(p);
        } else {
            portfolioRepo.save(p);
        }

        return "Stock sold successfully";
    }

    // CHECK BALANCE
    public double getBalance() {
        return balance;
    }
}