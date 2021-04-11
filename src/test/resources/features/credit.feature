Feature: Credit Assessment Calculator
  This is a simple credit assessment calculator feature
      
  Scenario Outline: Calculate Credit Assessment Score
    When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, <companyType>, <numberOfYearsOperated> parameters
    Then The credit assessment score should match <creditScore>

  Examples:
    | numberOfEmployees | companyType | numberOfYearsOperated | creditScore |
    | 6 | "Sole Proprietorship" | 5 | 7 |
    | 10 | "Limited Liability Company" | 8 | 13 |

	Scenario Outline: Calculate Credit Assessment Score and inputs are invalid
    When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, <companyType>, <numberOfYearsOperated> parameters
    Then The response http code shoud be <httpCode>
    
    Examples:
    | numberOfEmployees | companyType | numberOfYearsOperated | httpCode |
    | 6 | "SoleProprietorship" | 5 | 400 |
    
 	Scenario Outline: Calculate Credit Assessment Score and the user is not authenticated
    When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, <companyType>, <numberOfYearsOperated> parameters and the user is not authenticated
    Then The response http code shoud be <httpCode>

  Examples:
    | numberOfEmployees | companyType | numberOfYearsOperated | httpCode |
    | 6 | "Sole Proprietorship" | 5 | 403 |
    
  Scenario Outline: Calculate Credit Assessment Score and the user is not authorized
    When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, <companyType>, <numberOfYearsOperated> parameters and the user is not authorized
    Then The response http code shoud be <httpCode>

  Examples:
    | numberOfEmployees | companyType | numberOfYearsOperated | httpCode |
    | 6 | "Sole Proprietorship" | 5 | 401 |
    