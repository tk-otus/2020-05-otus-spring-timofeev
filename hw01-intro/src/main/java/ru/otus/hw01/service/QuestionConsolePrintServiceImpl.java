package ru.otus.hw01.service;

import ru.otus.hw01.domain.Answer;
import ru.otus.hw01.domain.Question;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class QuestionConsolePrintServiceImpl implements QuestionConsolePrintService {
    private final PrintStream out;

    public QuestionConsolePrintServiceImpl(OutputStream out) {
        this.out = new PrintStream(out);
    }

    @Override
    public void print(Question question) {
        out.println("Question: " + question.getQuestionText() + " (type: " + question.getType() + ")");
        for (Answer answer : question.getAnswers()) {
            out.println(" - " + answer.getAnswer() + " (is correct: " + answer.isCorrect() + ")");
        }
        out.println("");
    }

    @Override
    public void print(List<Question> questions) {
        questions.forEach(this::print);
    }
}
