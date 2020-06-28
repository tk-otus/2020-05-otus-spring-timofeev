package ru.otus.hw03.service.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public interface PrintService {

    void print(String string);

    String read();

}
