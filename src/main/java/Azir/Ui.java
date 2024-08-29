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
    public void showWelcome() {
        System.out.println("----------------------------------");
        System.out.println("Hello! I'm Azir");
        System.out.println("What can I do for you?");
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
     * Outputs message for the list command
     */
    public void showCommandEndMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Outputs message for all commands except list
     *
     * @param command User input
     * @param task Corresponding task for command
     */
    public void showCommandEndMessage(String command, String task) {
        switch (command) {
        case "mark":
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
            break;

        case "unmark":
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
            break;

        case "delete":
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            break;

        case "todo":
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            break;

        case "deadline":
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            break;

        case "event":
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            break;
        }
    }

    /**
     * Outputs number of tasks in tasklist
     *
     * @param size Size of task list
     */
    public void showTaskNumber(int size) {
        System.out.printf("Now you have %d %s in the list\n", size, size == 1 ? "task" : "tasks");
    }

    /**
     * Outputs the error message
     *
     * @param message The error message
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
