package jbot.util;

import java.util.Scanner;

import jbot.command.JBotCommand;

public class Ui {
    private Ui() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    private static final Scanner sc = new Scanner(System.in);
    public static void close() {
        sc.close();
    }
    private static void hLine() {
        System.out.println("________________________________________");
    }

    public static void greetUser() {
        hLine();
        System.out.println("Hello! I'm JBot");
        System.out.println("What can I do for you?");
        hLine();
    }
    public static void display(JBotCommand command, String userInput) {
        hLine();
        command.run(userInput);
        hLine();
    }

    public static String readInput() {
        return sc.nextLine();
    }

    public static void handleError(Exception e) {
        hLine();
        System.out.println(e.getMessage());
        hLine();
    }
}
