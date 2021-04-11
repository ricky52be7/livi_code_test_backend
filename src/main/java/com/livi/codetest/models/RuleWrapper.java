package com.livi.codetest.models;

public abstract class RuleWrapper<T> {
	public abstract boolean checkRule(T valueOfT);

	protected int value;
	
	public int getValue() {
		return value;
	}
}
