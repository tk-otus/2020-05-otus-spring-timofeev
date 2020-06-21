package ru.otus.hw02.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleCsvReader {
    private final CSVReader csvReader;

    public SimpleCsvReader(InputStreamReader reader) {
        this.csvReader = new CSVReader(reader);
    }

    public List<String[]> getResults() throws IOException, CsvValidationException {
        List<String[]> result = new ArrayList<>();
        String[] values;

        csvReader.readNext(); // Пропускаем строку с заголовками

        while ((values = csvReader.readNext()) != null) {
            result.add(values);
        }
        return result;
    }
}
