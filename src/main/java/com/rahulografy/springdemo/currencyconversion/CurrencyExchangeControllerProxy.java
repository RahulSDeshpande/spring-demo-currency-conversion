package com.rahulografy.springdemo.currencyconversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rahulografy.springdemo.currencyconversion.bean.ConversionValue;

@FeignClient(name = "service-exchange-conversion", url = "localhost:8000")
public interface CurrencyExchangeControllerProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ConversionValue getExchangeValue(@PathVariable String from, @PathVariable String to);
}