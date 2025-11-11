package org.example.result;

import java.math.BigDecimal;

public class BillResult<T> extends AbstractResult<T>{
    private BigDecimal total;

    public BillResult() {
        super();
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
