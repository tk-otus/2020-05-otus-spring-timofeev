package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.domain.Answer;
import ru.otus.hw01.domain.Question;
import ru.otus.hw01.service.AnswerService;
import ru.otus.hw01.service.QuestionService;

public class Main {
    public static void main(String[] args) {
        final var context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionService questionService = context.getBean(QuestionService.class);
        AnswerService answerService = context.getBean(AnswerService.class);

        for (Question question : questionService.getAll()) {
            for (Answer answer : answerService.getByQuestionId(question.getId())) {
                question.addAnswer(answer);
            }
        }

        for (Question question : questionService.getAll()) {
            System.out.println("Question: " + question.getQuestionText() + " (type: " + question.getType() + ")");
            for (Answer answer : question.getAnswers()) {
                System.out.println(" - " + answer.getAnswer() + " (is correct: " + answer.isCorrect() + ")");
            }
            System.out.println("");
        }
    }
}
