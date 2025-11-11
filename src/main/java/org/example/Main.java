package org.example;

import org.example.line.BillLine;
import org.example.line.ProductLine;
import org.example.result.BillResult;
import org.example.result.ReadResult;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        CsvProductReader csvProductReader = new CsvProductReader();
        BillCalculator billCalculator = new BillCalculator();
        CsvBillWriter csvBillWriter = new CsvBillWriter();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("The csv file from which it will read");
            String readable = scanner.nextLine();
            ReadResult<ProductLine> read = csvProductReader.read(readable);
            BillResult<BillLine> calculate = billCalculator.calculate(read.getItems());
            String writable = scanner.nextLine();
            System.out.println("The csv file written as");
            csvBillWriter.write(writable, calculate.getItems(), calculate.getTotal(), read.getErrorCount());
        }
    }
}
