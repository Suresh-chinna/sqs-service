package com.sqs.process.model;

import java.math.BigDecimal;

public class CreateExpenseDto {
	private String type;
	private BigDecimal amount;

	public CreateExpenseDto() {
	}

	public CreateExpenseDto(String type, BigDecimal amount) {
		super();
		this.type = type;
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CreateExpenseDto [type=" + type + ", amount=" + amount + "]";
	}

}
