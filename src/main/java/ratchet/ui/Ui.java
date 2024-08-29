package ratchet.ui;

import java.util.Scanner;

public class Ui {
    private static final String INDENT = "    ";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String read() {
        String input = scanner.nextLine();
        return input;
    }

    public void greet() {
        printLine();
        printWithIndent("Hello! I'm Ratchet\n" + INDENT + "What can I do for you?");
        printLine();
    }

    public void exit() {
        printLine();
        printWithIndent("Bye. Hope to see you again soon!");
        printLine();
    }

    public void error(String message) {
        printLine();
        printWithIndent("ERROR: " + message);
        printLine();
    }

    public void printLine() {
        System.out.println("    ________________________________________________________");
    }

    public void printWithIndent(String message) {
        System.out.println(INDENT + message);
    }
}
