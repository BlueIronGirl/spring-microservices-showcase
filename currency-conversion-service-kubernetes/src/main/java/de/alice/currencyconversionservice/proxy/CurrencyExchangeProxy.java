package de.alice.currencyconversionservice.proxy;

import de.alice.currencyconversionservice.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Proxy for the currency-exchange microservice
 * When a deployment in kubernetes is created it also creates a system property of it with it's service host.
 * <p></p>
 * In First Option the service host of the currency-exchange-service can be determined with the system property "CURRENCY_EXCHANGE_SERVICE_HOST"
 * When the system property can't be found (for example when it runs on a local machine) the default value is "http://localhost".
 * <p></p>
 * In Second Option it also works when the currency-exchange-service is not available (so that the CURRENCY_EXCHANGE_SERVICE_HOST is not set).
 * For that option a custom environment variable in the deployment.yaml is needed.
 */
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000") // Option 1: with kubernetes service discovery (Kubernetes Service Property)
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
// Option 2: with kubernetes service discovery (system property needed)
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
