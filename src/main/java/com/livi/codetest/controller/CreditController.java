package com.livi.codetest.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.livi.codetest.response.CreditCalculatorResponse;
import com.livi.codetest.service.CreditService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "oauth2Scheme")
@RestController
@RequestMapping("creditservice/v1")
public class CreditController {
	@Autowired
	private CreditService creditService;
	
	@PostMapping("/calculator")
	public CreditCalculatorResponse calculator(
			@RequestParam("numberOfEmployees") int noOfEmployees,
			@RequestParam("companyType")  String companyType,
			@RequestParam("numberOfYearsOperated") int noOfYearsOperated,
			HttpServletResponse response
	) {
		Integer result = creditService.calculator(noOfEmployees, companyType, noOfYearsOperated);
		if (result != null) {
			return new CreditCalculatorResponse(result);	
		} else {
			response.setStatus(400);
			return null;
		}
		
	}
}
