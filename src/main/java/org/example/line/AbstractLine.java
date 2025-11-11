package org.example.line;
import static org.example.Constants.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class AbstractLine {
    private final String name;
    private final Integer quantity;
    private final BigDecimal unitPrice;

    public AbstractLine(String name, Integer quantity, BigDecimal unitPrice) {
        this.name = validateName(name);
        this.quantity = validateQuantity(quantity);
        this.unitPrice = validateUnitPrice(unitPrice).setScale(2, RoundingMode.HALF_UP);
    }

    private String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(NOT_VALID_NAME);
        }
        return name.trim();
    }

    private Integer validateQuantity(Integer quantity) {
        if (quantity == null || quantity < 0) {
            throw new IllegalArgumentException(NOT_VALID_QUANTITY_NAME);
        }
        return quantity;
    }

    private BigDecimal validateUnitPrice(BigDecimal unitPrice) {
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(NOT_VALID_PRICE);
        }
        return unitPrice;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }


}
