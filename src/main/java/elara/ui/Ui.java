package elara.ui;

import java.util.ArrayList;
import java.util.Scanner;

import elara.task.InvalidInputException;
import elara.task.Task;
import elara.task.TaskList;

/**
 * Represents the user interface of the Elara chatbot.
 * It handles input/output between the user and the system.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Constructs a new Ui instance.
     * Initializes the scanner to read user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads user input from the console.
     *
     * @return the user input as a string.
     */
    public String readInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message at the start of the application.
     */
    public void showWelcomeMessage() {
        String welcomeMessage = "Hello! I'm Elara\nWhat can I do for you?";
        System.out.println(LINE + "\n" + welcomeMessage + "\n" + LINE);
    }

    /**
     * Displays the exit message when the user exits the application.
     */
    public void showExitMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(LINE + "\n" + goodbyeMessage + "\n" + LINE);
    }

    /**
     * Displays a message confirming that a new task has been added.
     *
     * @param task the task that has been added.
     * @param taskList the list of tasks.
     */
    public void showAddTaskMessage(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list%n", taskList.getSize());
    }

    /**
     * Displays the number of tasks currently in the list.
     *
     * @param taskList the list of tasks.
     */
    public void showNumOfTasksMessage(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list%n", taskList.getSize());
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task the task that has been marked as done.
     */
    public void showMarkedTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Displays a message confirming that a task has been unmarked (not done).
     *
     * @param task the task that has been unmarked.
     */
    public void showUnmarkedTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Displays a message confirming that a task has been removed from the list.
     *
     * @param task the task that has been removed.
     */
    public void showRemoveTaskMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Lists all tasks currently in the task list.
     *
     * @param taskList the list of tasks.
     */
    public void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i) == null) {
                break;
            }
            System.out.println((i + 1) + ". " + taskList.getTask(i));
        }
    }

    /**
     * Displays an error message when an invalid command is entered.
     *
     * @param e the exception thrown due to an invalid command.
     */
    public void showInvalidCommandMessage(InvalidInputException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Closes the scanner used to read user input.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays the tasks that match the search keyword.
     *
     * @param taskList the list of tasks that match the search keyword.
     */
    public void showFindTasksMessage(ArrayList<Task> taskList) {
        System.out.println(LINE);
        if (taskList.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
        System.out.println(LINE);
    }
}
