package froggy;

import java.util.Scanner;

public class Ui {
    private static final String GREETING = """
            Hello! I'm Froggy!
            Enter tasks and I will store it.
            Type 'list' to view tasks or 'bye' to exit.
            _______________________________""";
    private static final String EXIT = "Bye. Hope to see you again soon!\n"
            + "_______________________________";
    private static final String LINE = "_______________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getGreeting() {
        return GREETING;
    }

    public void showGreeting() {
        System.out.println(GREETING);
    }

    public String getExit() {
        return EXIT;
    }

    public void showExit() {
        System.out.println(EXIT);
    }

    public String getLine() {
        return LINE;
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void show(String output) {
        System.out.println(output);
    }

    public void close() {
        scanner.close();
    }


}
