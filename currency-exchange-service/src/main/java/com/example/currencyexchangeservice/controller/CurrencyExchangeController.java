package com.example.currencyexchangeservice.controller;

import com.example.currencyexchangeservice.bean.CurrencyExchange;
import com.example.currencyexchangeservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private final Environment environment;
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyExchangeController(Environment environment, CurrencyService currencyService) {
        this.environment = environment;
        this.currencyService = currencyService;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyService.retrieveExchangeValue(from, to);

        String serverPort = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(serverPort);

        return currencyExchange;
    }
}
