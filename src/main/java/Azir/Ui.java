package Azir;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user
 */
public class Ui {
    /**
     * Outputs the divider line
     */
    public void showLine() {
        System.out.println("----------------------------------");
    }

    /**
     * Outputs the chatbot welcome message
     */
    public static String showWelcome() {
        String response = "";
        response += "Hello! I'm Azir\n";
        response += "What can I do for you?";
        return response;
    }

    /**
     * Outputs the chatbot exit message
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the user input
     *
     * @param obj Scanner object for reading user input
     * @return User input
     */
    public String readCommand(Scanner obj) {
        String command = obj.nextLine();
        return command;
    }

    /**
     * Outputs message for list and find command
     */
    public String showCommandEndMessage(String command) {
        switch (command) {
        case "list":
            return "Here are the tasks in your list:\n";

        case "find":
            return "Here are the matching tasks in your list:\n";

        default:
            return "";
        }
    }

    /**
     * Outputs message for all commands except list
     *
     * @param command User input
     * @param task Corresponding task for command
     */
    public String showCommandEndMessage(String command, String task) {
        String botOutput = "";
        switch (command) {
        case "mark":
            botOutput += "Nice! I've marked this task as done:\n";
            botOutput += task + "\n";
            return botOutput;

        case "unmark":
            botOutput += "OK, I've marked this task as not done yet:\n";
            botOutput += task + "\n";
            return botOutput;

        case "delete":
            botOutput += "Noted. I've removed this task:\n";
            botOutput += task + "\n";
            return botOutput;

        case "todo":
            botOutput += "Got it. I've added this task:\n";
            botOutput += task + "\n";
            return botOutput;

        case "deadline":
            botOutput += "Got it. I've added this task:\n";
            botOutput += task + "\n";
            return botOutput;

        case "event":
            botOutput += "Got it. I've added this task:\n";
            botOutput += task + "\n";
            return botOutput;

        default:
            return "";
        }
    }

    /**
     * Outputs number of tasks in tasklist
     *
     * @param size Size of task list
     */
    public String showTaskNumber(int size) {
        return String.format("Now you have %d %s in the list\n", size, size == 1 ? "task" : "tasks");
    }

    /**
     * Outputs the error message
     *
     * @param message The error message
     */
    public String showError(String message) {
        return message;
    }
    //test
}
