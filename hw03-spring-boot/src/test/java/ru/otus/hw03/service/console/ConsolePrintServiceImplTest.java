package ru.otus.hw03.service.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сервис ConsolePrintServiceImplTest")
class ConsolePrintServiceImplTest {
    private ByteArrayOutputStream bos;
    private ConsolePrintServiceImpl printService;

    @BeforeEach
    void setUp() {
        bos = new ByteArrayOutputStream();
        printService = new ConsolePrintServiceImpl(new PrintStream(bos), System.in);
    }

    @Test
    @DisplayName("Может печатать одну строку")
    void testPrintOneLine() throws InterruptedException {
        final String text = "Hello, World!";
        printService.print(text);
        assertEquals(text + System.lineSeparator(), bos.toString());
    }

    @Test
    @DisplayName("Может печатать две строки")
    void testPrintMultipleLines() throws InterruptedException {
        final String text = "Hello, World!" + System.lineSeparator() + "New line";
        printService.print(text);
        assertEquals(text + System.lineSeparator(), bos.toString());
    }

    @Test
    @DisplayName("Может прочитать пользовательский ввод")
    void testRead() {
        final String text = "User text";
        var bis = new ByteArrayInputStream(text.getBytes());
        printService = new ConsolePrintServiceImpl(new PrintStream(bos), bis);
        assertEquals(text, printService.read());
    }
}
