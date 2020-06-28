package ru.otus.hw02.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleCsvReader {

    public List<String[]> getResults(Resource file) throws IOException, CsvValidationException {
        var csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
        List<String[]> result = new ArrayList<>();
        String[] values;

        csvReader.readNext(); // Пропускаем строку с заголовками

        while ((values = csvReader.readNext()) != null) {
            result.add(values);
        }
        return result;
    }
}
