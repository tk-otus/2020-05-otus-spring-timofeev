package ru.otus.hw02.service;

import ru.otus.hw02.domain.Question;

import java.util.List;

public interface QuestionConsolePrintService {

    void print(Question question);

    void print(List<Question> questions);

}
