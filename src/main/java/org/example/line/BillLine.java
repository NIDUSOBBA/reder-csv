package org.example.line;

import java.math.BigDecimal;
import java.math.RoundingMode;


import static org.example.Constants.*;

public class BillLine extends AbstractLine {
    private final boolean discountApplied;
    private final BigDecimal finalTotal;

    public BillLine(String name, Integer quantity, BigDecimal unitPrice, boolean discountApplied, BigDecimal finalTotal) {
        super(name, quantity, unitPrice);
        this.discountApplied = discountApplied;
        this.finalTotal = validateFinalTotal(finalTotal).setScale(2, RoundingMode.HALF_UP);
    }
    private BigDecimal validateFinalTotal(BigDecimal unitPrice) {
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(NOT_VALID_PRICE);
        }
        return unitPrice;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }

    public BigDecimal getFinalTotal() {
        return finalTotal;
    }

}
