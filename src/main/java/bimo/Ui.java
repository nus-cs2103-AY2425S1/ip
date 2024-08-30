package bimo;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private static String LINE = "    " + "___________________________________";

    /**
     * Displays introduction to users.
     *
     * @param NAME Name of the chatbot.
     */
    public void greetUser(String NAME) {
        System.out.println(LINE);
        System.out.println("    " + String.format("Hello! I'm %s", NAME));
        System.out.println("    " + "What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Retrieves user input System.in.
     *
     * @return User input
     */
    public String getUserCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Displays error message for invalid tasks index.
     */
    public void showTaskNotFoundError() {
        System.out.println("    Bimo.Tasks.Task not found in list");
    }

    /**
     * Displays error message for invalid commands.
     */
    public void showErrorMessage() {
        System.out.println("    Something went wrong! Please try again");
    }

}