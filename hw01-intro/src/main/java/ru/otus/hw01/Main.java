package ru.otus.hw01;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        final var context = new ClassPathXmlApplicationContext("/spring-context.xml");
    }
}
