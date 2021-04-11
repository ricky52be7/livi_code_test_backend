package com.livi.codetest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livi.codetest.enums.RuleType;
import com.livi.codetest.models.CompanyTypeRule;
import com.livi.codetest.models.EmployeeRule;
import com.livi.codetest.models.YearsOperatedRule;
import com.livi.codetest.service.CreditService;
import com.livi.codetest.utils.CreditUtils;

@Service
public class CreditServiceImpl implements CreditService {
	@Autowired
	private CreditUtils creditUtils;

	@Override
	public Integer calculator(int noOfEmployees, String companyType, int noOfYearsOperated) {
		EmployeeRule employeeRule = (EmployeeRule) creditUtils.getRuleFromList(RuleType.NUMBER_OF_EMPLOYEE, noOfEmployees);
		CompanyTypeRule companyTypeRule = (CompanyTypeRule) creditUtils.getRuleFromList(RuleType.COMPANY_TYPE, companyType);
		YearsOperatedRule yearOperatedRule = (YearsOperatedRule) creditUtils.getRuleFromList(RuleType.NUMBER_OF_YEARS_OPERATED, noOfYearsOperated);

		if (employeeRule == null || companyTypeRule == null || yearOperatedRule == null) {
			return null;
		}
		
		return creditUtils.calculateCreditAssessmentScore(employeeRule, companyTypeRule, yearOperatedRule);
	}

}
