package com.rahulografy.springdemo.currencyconversion.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rahulografy.springdemo.currencyconversion.bean.ConversionValue;

@RestController
public class CurrencyConversionController {

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionValue getExchangeValue(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<ConversionValue> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", ConversionValue.class, uriVariables);

		ConversionValue conversionValue = responseEntity.getBody();

		return new ConversionValue(conversionValue.getId(), conversionValue.getFrom(), conversionValue.getTo(),
				conversionValue.getConversionMultiple(), quantity,
				quantity.multiply(conversionValue.getConversionMultiple()), conversionValue.getPort());
	}
}