package bimo;

import java.util.Scanner;

public class Ui {
    private static String LINE = "    " + "___________________________________";

    public Ui() {
    }

    /**
     * Displays introduction to users
     * @param NAME Name of the chatbot
     */
    public void greetUser(String NAME) {
        System.out.println(LINE);
        System.out.println("    " + String.format("Hello! I'm %s", NAME));
        System.out.println("    " + "What can I do for you?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Retrieves user command from input string
     * @return String user command
     */
    public String getUserCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public void showTaskNotFoundError() {
        System.out.println("    Bimo.Tasks.Task not found in list");
    }

    public void showErrorMessage() {
        System.out.println("    Something went wrong! Please try again");
    }

}