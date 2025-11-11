package org.example;

import org.example.line.BillLine;
import org.example.line.ProductLine;
import org.example.result.BillResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class BillCalculator {
    private final DiscountPolicy discountPolicy = new DiscountPolicy();

    public BillResult<BillLine> calculate(ArrayList<ProductLine> products) {
        BillResult<BillLine> billResult = new BillResult<>();
        BigDecimal total = BigDecimal.ZERO;
        for (ProductLine product : products) {
            boolean found = product.getQuantity() >= 3;
            BigDecimal subtotal = product.getUnitPrice().multiply(new BigDecimal(product.getQuantity()));
            BigDecimal finalTotal = discountPolicy.discountFor(subtotal, product.getQuantity());
            total = total.add(finalTotal);
            billResult.addItems(createBillLine(product, found, finalTotal));
        }
        total = total.setScale(2, RoundingMode.HALF_UP);
        billResult.setTotal(total);
        return billResult;
    }

    private BillLine createBillLine(ProductLine product, boolean found, BigDecimal finalTotal) {
        return new BillLine(
                product.getName(),
                product.getQuantity(),
                product.getUnitPrice(),
                found,
                finalTotal
        );
    }
}
