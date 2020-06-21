package ru.otus.hw03.service.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public interface ConsolePrintService {

    public void setOut(PrintStream out);

    public void setIn(InputStream in);

    void print(String string);

    public String read() throws IOException;

}
