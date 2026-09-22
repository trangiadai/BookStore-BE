package com.tgd.enums;

import java.math.BigDecimal;

public enum ShippingFee {
	STADARD_FEE(BigDecimal.valueOf(30000));

    private final BigDecimal fee;

	ShippingFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getFee() {
        return fee;
    }
}
