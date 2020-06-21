package ru.otus.hw02.service.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw03.service.console.ConsolePrintService;
import ru.otus.hw03.service.console.ConsolePrintServiceImpl;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сервис ConsolePrintServiceImplTest")
class ConsolePrintServiceImplTest {
    private static final String TEXT_TO_PRINT1 = "Hello, World!";
    private static final String TEXT_TO_PRINT2 = "Hello, World!" + System.lineSeparator() + "New line";
    private static final String TEXT_TO_READ = "User text";
    private ByteArrayOutputStream bos;
    private ConsolePrintService printService;

    @BeforeEach
    void setUp() {
        bos = new ByteArrayOutputStream();
        printService = new ConsolePrintServiceImpl(new PrintStream(bos), System.in);
    }

    @Test
    @DisplayName("Может печатать одну строку")
    void testPrint1() throws InterruptedException {
        printService.print(TEXT_TO_PRINT1);
        assertEquals(TEXT_TO_PRINT1 + System.lineSeparator(), bos.toString());
    }

    @Test
    @DisplayName("Может печатать две строки")
    void testPrint2() throws InterruptedException {
        printService.print(TEXT_TO_PRINT2);
        assertEquals(TEXT_TO_PRINT2 + System.lineSeparator(), bos.toString());
    }

    @Test
    @DisplayName("Может прочитать пользовательский ввод")
    void testRead() throws IOException {
        var bis = new ByteArrayInputStream(TEXT_TO_READ.getBytes());
        printService.setIn(bis);
        assertEquals(TEXT_TO_READ, printService.read());
    }
}
