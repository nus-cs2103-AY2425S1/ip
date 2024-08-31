package zbot;

import java.util.Scanner;

import zbot.task.Task;

/**
 * Represents the user interface of the chatbot.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message.
     */
    public void intro() {
        System.out.println("Hello! I'm ZBot!");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints the exit message.
     */
    public void outro() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Prints additional message after adding a task.
     *
     * @param task
     * @param size
     */
    public void printAddTaskMsg(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints additional message after deleting a task.
     *
     * @param task
     * @param size
     */
    public void printDeleteTaskMsg(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints additional message after marking a task as done.
     *
     * @param task
     */
    public void printMarkTaskMsg(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println();
    }

    /**
     * Prints additional message after marking a task as not done.
     *
     * @param task
     */
    public void printUnmarkTaskMsg(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println();
    }

    /**
     * Prints the error message when the no saved file is found.
     */
    public void printLoadingError() {
        System.out.println("No saved data found. Starting with an empty task list...\n");
    }

    /**
     * Reads the input from the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
