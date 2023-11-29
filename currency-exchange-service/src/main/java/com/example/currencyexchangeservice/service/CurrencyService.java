package com.example.currencyexchangeservice.service;

import com.example.currencyexchangeservice.bean.CurrencyExchange;
import com.example.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    private final CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public CurrencyService(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    public CurrencyExchange retrieveExchangeValue(String from, String to) {
        return currencyExchangeRepository.findByFromAndTo(from, to).orElseThrow(() -> new RuntimeException("Unable to Find data for " + from + " to " + to));
    }
}
