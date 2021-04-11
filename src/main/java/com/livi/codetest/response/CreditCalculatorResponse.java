package com.livi.codetest.response;

public class CreditCalculatorResponse {
	private int creditScore;
	
	public CreditCalculatorResponse() {
	}
	
	public CreditCalculatorResponse(int creditScore) {
		this.creditScore = creditScore;
	}
	
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	
	public int getCreditScore() {
		return creditScore;
	}
}
