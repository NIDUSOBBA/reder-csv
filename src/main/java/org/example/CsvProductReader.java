package org.example;

import org.example.line.ProductLine;
import org.example.result.ReadResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.example.Constants.*;

public class CsvProductReader {

    private char detectDelimiter(String line) {
        int commaCount = countOccurrences(line, ',');
        int semicolonCount = countOccurrences(line, ';');
        int tabCount = countOccurrences(line, '\t');
        int pipeCount = countOccurrences(line, '|');

        Map<Character, Integer> delimiters = new HashMap<>();
        delimiters.put(',', commaCount);
        delimiters.put(';', semicolonCount);
        delimiters.put('\t', tabCount);
        delimiters.put('|', pipeCount);

        return Collections.max(delimiters.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }

    private int countOccurrences(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }

    public ReadResult<ProductLine> read(String path) throws IOException {
        pathValid(path);
        ReadResult<ProductLine> result = new ReadResult<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int lineNumber = 0;
            String line;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                char delimiter = detectDelimiter(line);
                String[] lineArray = line.split(String.valueOf(delimiter));
                if (lineArray[0].startsWith("#") || lineArray[0].startsWith(" ") || lineArray.length < 3) {
                    result.additionErrorCount();
                } else {
                    try {
                        String name = lineArray[0];
                        Integer quantity = Integer.valueOf(lineArray[1]);
                        BigDecimal price = new BigDecimal(lineArray[2]);
                        result.addItems(new ProductLine(name, quantity, price));
                    } catch (Exception e) {
                        System.err.printf(ERROR_LINE, lineNumber, e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(ERROR_READING + ": " + e.getMessage());
        }
        return result;
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

}
