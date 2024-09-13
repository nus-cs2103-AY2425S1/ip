package main.java.angel;

import java.util.List;
import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The input string from the user.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to print.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints an error message to the user.
     *
     * @param errorMessage The error message to print.
     */
    public void printError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Prints a task list to the user.
     *
     * @param taskList The list of tasks to print.
     */
    public void printTasks(TaskList taskList) {
        print("Here are the tasks in your list:");
        for (int i = 0; i < taskList.listTasks().size(); i++) {
            print((i + 1) + "." + taskList.listTasks().get(i));
        }
    }

    /**
     * Prints the results of a search query to the user.
     *
     * @param keyword The keyword used for the search.
     * @param results The list of tasks matching the search query.
     */
    public void printFindResults(String keyword, List<Task> results) {
        if (results.isEmpty()) {
            print("No tasks found containing: " + keyword);
        } else {
            print("Here are the matching tasks in your list:");
            for (int i = 0; i < results.size(); i++) {
                print((i + 1) + "." + results.get(i));
            }
        }
    }

    /**
     * Prints a list of found tasks.
     *
     * @param tasks The list of tasks to print.
     */
    public void printFoundTasks(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}
