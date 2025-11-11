package org.example;
import java.math.BigDecimal;
import static org.example.Constants.*;

public class DiscountPolicy {

    public BigDecimal discountFor(BigDecimal subtotal, int quantity) {
        if(quantity >= QUANTITY_DISCOUNT) {
            return subtotal.multiply(new BigDecimal(TEN_PERCENT_DISCOUNT));
        }
        return subtotal;
    }
}
