package ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printLine() {
        String line = "_________________________________________";
        System.out.println(line);
    }

    public void showWelcome() {
        printLine();
        System.out.println("Hello! I'm PandaBot.");
        System.out.println("What can I do for you?");
        System.out.println("Type 'help' if you are unsure of how to get started!");
        printLine();
    }

    public void showGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
