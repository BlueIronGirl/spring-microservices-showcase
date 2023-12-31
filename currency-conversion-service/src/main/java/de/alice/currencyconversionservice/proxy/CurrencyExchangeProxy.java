package de.alice.currencyconversionservice.proxy;

import de.alice.currencyconversionservice.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Proxy for the currency-exchange microservice
 */
//@FeignClient(name = "currency-exchange", url = "localhost:8000") // with hardcoded url
@FeignClient(name = "currency-exchange") // with service discovery
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
