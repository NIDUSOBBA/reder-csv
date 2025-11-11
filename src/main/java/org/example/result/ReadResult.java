package org.example.result;


public class ReadResult<T> extends AbstractResult<T> {
    private Integer errorCount;

    public ReadResult() {
        super();
        errorCount = 0;
    }

    public void additionErrorCount() {
        errorCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }

}
