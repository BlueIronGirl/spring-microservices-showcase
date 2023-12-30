package com.example.currencyexchangeservice.service;

import com.example.currencyexchangeservice.bean.CurrencyExchange;
import com.example.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final CurrencyExchangeRepository currencyExchangeRepository;
    private final Environment environment;

    @Autowired
    public CurrencyService(CurrencyExchangeRepository currencyExchangeRepository, Environment environment) {
        this.currencyExchangeRepository = currencyExchangeRepository;
        this.environment = environment;
    }

    public CurrencyExchange retrieveExchangeValue(String from, String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to).orElseThrow(() -> new RuntimeException("Unable to Find data for " + from + " to " + to));

        String serverPort = environment.getProperty("local.server.port");
        String host = environment.getProperty("HOSTNAME");
        String version = "v1";
        currencyExchange.setEnvironment(serverPort + " " + version + " " + host);

        return currencyExchange;
    }
}
