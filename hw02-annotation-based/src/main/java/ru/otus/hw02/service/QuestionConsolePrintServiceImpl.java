package ru.otus.hw02.service;

import org.springframework.stereotype.Service;
import ru.otus.hw02.domain.Answer;
import ru.otus.hw02.domain.Question;

import java.io.PrintStream;
import java.util.List;

@Service
public class QuestionConsolePrintServiceImpl implements QuestionConsolePrintService {
    private PrintStream out = new PrintStream(System.out);

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

    @Override
    public void print(String string) {
        out.println(string);
    }

    public void setOut(PrintStream out) {
        this.out = new PrintStream(out);
    }
}
