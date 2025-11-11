package org.example;

import org.example.line.BillLine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.example.Constants.PATH_EMPTY;
import static org.example.Constants.PATH_EXIST;

public class CsvBillWriter {
    public void write(String path, List<BillLine> lines, BigDecimal total,  int errors) {
        pathValid(path);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            for (BillLine billLine : lines) {
                bufferedWriter.write(String.join(";", creatLines(billLine)));
                bufferedWriter.newLine();
            }
            String createTotal = "TOTAL;;;;" + total;
            bufferedWriter.write(createTotal);
            bufferedWriter.newLine();
            String createErrors = "ERRORS;;;;" + errors;
            bufferedWriter.write(createErrors);
        } catch (IOException e) {
            System.err.println("Error write file: " + e.getMessage());
        }
    }
    private void pathValid(String path) {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException(PATH_EMPTY);
        }

        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException(PATH_EXIST);
        }
    }

    private List<String> creatLines(BillLine billLine) {
        List<String> line = new ArrayList<>();
        line.add(billLine.getName());
        line.add(String.valueOf(billLine.getQuantity()));
        line.add(String.valueOf(billLine.getUnitPrice()));
        int a = 0;
        if (billLine.isDiscountApplied()){
            a = 1;
        }
        line.add(String.valueOf(a));
        line.add(String.valueOf(billLine.getFinalTotal()));
        return line;
    }
}
