package ui;

import commands.Command;
import parser.Parser;

import java.util.Scanner;

public class CliUi implements Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Ip Man.");
        System.out.println("What can I do for you?");
    }

    @Override
    public Command readMessage() {
        System.out.print("> ");
        String message = scanner.nextLine();
        return Parser.parseCommand(message);
    }

    @Override
    public void showError(String message) {
        System.out.println("Oh no! Something went wrong:");
        System.out.println(message);
    }

    @Override
    public void showGoodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("That's enough for today. See you another time.");
        System.out.println(HORIZONTAL_LINE);
    }
}
