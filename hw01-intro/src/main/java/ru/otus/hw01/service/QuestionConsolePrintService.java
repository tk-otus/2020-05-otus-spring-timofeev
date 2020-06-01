package ru.otus.hw01.service;

import ru.otus.hw01.domain.Question;

import java.util.List;

public interface QuestionConsolePrintService {

    void print(Question question);

    void print(List<Question> questions);

}
