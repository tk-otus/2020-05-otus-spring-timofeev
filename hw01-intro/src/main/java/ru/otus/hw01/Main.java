package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw01.domain.Answer;
import ru.otus.hw01.domain.Question;
import ru.otus.hw01.service.QuestionService;

public class Main {
    public static void main(String[] args) {
        final var context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionService questionService = context.getBean(QuestionService.class);

        for (Question question : questionService.getAll()) {
            System.out.println(question.getQuestionText());
            for (Answer answer : question.getAnswers()) {
                System.out.println(" - " + answer.getAnswer());
            }
            System.out.println("");
        }
    }
}
