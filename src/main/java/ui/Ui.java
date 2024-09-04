package ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private final String name = "Mylo";
    public void separator() {
        System.out.println("--------------------------------");
    }

    /**
     * Prints the Goodbye message and terminates the program.
     */
    public void exit() {
        String goodbye = "Goodbye. Have a nice day ahead!";
        System.out.println(goodbye);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcomeMessage() {
        String greet = "Hello! Thanks for using " + name + ".";
        String opening_query = "What can I help you?";

        System.out.println(greet);
        System.out.println(opening_query);
    }

    public void show(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        String input = scanner.nextLine();

        if (input.isBlank()) return "bye";

        return input;
    }
}
