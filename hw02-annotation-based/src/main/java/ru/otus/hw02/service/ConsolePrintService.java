package ru.otus.hw02.service;

import ru.otus.hw02.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public interface ConsolePrintService {

    public void setOut(PrintStream out);

    public void setIn(InputStream in);

    void print(String string);

    public String read() throws IOException;

    void print(Question question);

    void print(List<Question> questions);
}
