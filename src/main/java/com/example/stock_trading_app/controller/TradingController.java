package com.example.stock_trading_app.controller;

import com.example.stock_trading_app.model.Stock;
import com.example.stock_trading_app.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trade")
public class TradingController {

    @Autowired
    private TradingService service;

    @PostMapping("/addStock")
    public Stock addStock(@RequestBody Stock stock){
        return service.addStock(stock);
    }

    @PostMapping("/buy")
    public String buy(@RequestParam String name, @RequestParam int qty) {
        return service.buy(name, qty);
    }

    @PostMapping("/sell")
    public String sell(@RequestParam String name, @RequestParam int qty) {
        return service.sell(name, qty);
    }

    @GetMapping("/balance")
    public double balance() {
        return service.getBalance();
    }
}
