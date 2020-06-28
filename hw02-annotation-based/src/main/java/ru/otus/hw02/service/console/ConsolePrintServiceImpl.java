package ru.otus.hw02.service.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsolePrintServiceImpl implements PrintService {
    private final PrintStream out;
    private final Scanner sc;

    @Autowired
    ConsolePrintServiceImpl(@Value("#{ T(java.lang.System).out}") PrintStream out,
                            @Value("#{ T(java.lang.System).in}") InputStream in) {
        this.out = out;
        this.sc = new Scanner(in);
    }

    @Override
    public void print(String string) {
        out.println(string);
    }

    public String read() {
        return sc.nextLine();
    }
}
