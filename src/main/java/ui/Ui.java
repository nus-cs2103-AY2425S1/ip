package ui;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * The Ui class handles all user interactions, including displaying messages and
 * receiving input from the user.
 * It manages the console output and input for the ChattyBuddy application.
 */
public class Ui {
    private final String BREAKLINE = "----------------------------";
    private final Scanner scanner;

    /**
     * Constructs a new Ui object, initializing the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println(BREAKLINE);
        String LOGO = " ____        _           \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm main.ChattyBuddy");
        System.out.println("What can I do for you?");
        System.out.println(BREAKLINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println(BREAKLINE);
        System.out.println("error: " + message);
        System.out.println(BREAKLINE);
    }

    /**
     * Displays a goodbye message to the user when exits.
     */
    public void showGoodbyeMessage() {
        System.out.println(BREAKLINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BREAKLINE);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println(BREAKLINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d.[%s][%s] %s%n", i + 1, taskList.get(i).getType(), taskList.get(i).getStatusIcon(), taskList.get(i));
        }
        System.out.println(BREAKLINE);
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkTask(Task task) {
        System.out.println(BREAKLINE);
        System.out.println("OK, I've marked this task as done:");
        System.out.printf("[%s][%s] %s%n", task.getType(), task.getStatusIcon(), task);
        System.out.println(BREAKLINE);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkTask(Task task) {
        System.out.println(BREAKLINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("[%s][%s] %s%n", task.getType(), task.getStatusIcon(), task);
        System.out.println(BREAKLINE);
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task The task that has been added.
     * @param taskListSize The current size of the task list after the task has been added successfully.
     */
    public void showAddTask(Task task, int taskListSize) {
        System.out.println(BREAKLINE);
        System.out.println("Got it. I've added this task:");
        System.out.printf("  [%s][%s] %s%n", task.getType(), task.getStatusIcon(), task);
        System.out.printf("Now you have %d tasks in the list%n", taskListSize);
        System.out.println(BREAKLINE);
    }

    /**
     * Displays a message indicating that a task has been removed from the task list.
     *
     * @param task The task that has been removed.
     * @param taskListSize The current size of the task list after the task has been removed successfully.
     */
    public void showDeleteTask(Task task, int taskListSize) {
        System.out.println(BREAKLINE);
        System.out.println("Noted. I've removed this task:");
        System.out.printf("[%s][%s] %s%n", task.getType(), task.getStatusIcon(), task);
        System.out.printf("Now you have %d tasks in the list%n", taskListSize);
        System.out.println(BREAKLINE);
    }

    /**
     * Reads a command from the user input.
     *
     * @return The command entered by the user as a String.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
