package main.java.chatbot;

import java.util.Scanner;

public class UI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String LINE = "____________________________________________________________";

    public void greet(String name) {
        output("Hello! I'm " + name + "\nWhat can I do for you?");
    }

    public void output(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}

