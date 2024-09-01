package jbot.util;

import java.util.Scanner;

import jbot.command.JBotCommand;

public class Ui {
    private Ui() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    private static final Scanner sc = new Scanner(System.in);
    public static void close() {
        Ui.sc.close();
    }
    private static void hLine() {
        System.out.println("________________________________________");
    }

    public static void greetUser() {
        Ui.hLine();
        System.out.println("Hello! I'm JBot");
        System.out.println("What can I do for you?");
        Ui.hLine();
    }
    public static void display(JBotCommand command, String userInput) {
        Ui.hLine();
        command.run(userInput);
        Ui.hLine();
    }

    public static String readInput() {
        return Ui.sc.nextLine();
    }

    public static void handleError(Exception e) {
        Ui.hLine();
        System.out.println(e.getMessage());
        Ui.hLine();
    }
}
