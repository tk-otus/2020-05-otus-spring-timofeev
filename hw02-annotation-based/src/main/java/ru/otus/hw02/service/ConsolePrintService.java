package ru.otus.hw02.service;

import ru.otus.hw02.domain.Question;

import java.io.PrintStream;
import java.util.List;

public interface ConsolePrintService {

    void print(Question question);

    void print(List<Question> questions);

    void print(String string);

    void setOut(PrintStream out);
}
