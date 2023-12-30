package de.alice.currencyconversionservice.proxy;

import de.alice.currencyconversionservice.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Proxy for the currency-exchange microservice
 * When a deployment in kubernetes is created it also creates a system property of it with it's service host.
 * Here the service host of the currency-exchange-service can be determined with the system property "CURRENCY_EXCHANGE_SERVICE_HOST"
 * When the system property can't be found (for example when it runs on a local machine) the default value is "http://localhost".
 */
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000") // with kubernetes service discovery
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
