package ru.otus.hw02.service;

import org.springframework.stereotype.Service;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Question;

import java.io.*;
import java.util.List;

@Service
public class ConsolePrintServiceImpl implements ConsolePrintService {
    private PrintStream out = new PrintStream(System.out);
    private InputStreamReader in = new InputStreamReader(System.in);

    @Override
    public void setOut(PrintStream out) {
        this.out = new PrintStream(out);
    }

    @Override
    public void setIn(InputStream in) {
        this.in = new InputStreamReader(in);
    }

    @Override
    public void print(String string) {
        out.println(string);
    }

    public String read() throws IOException {
        BufferedReader reader = new BufferedReader(in);
        return reader.readLine();
    }
}
