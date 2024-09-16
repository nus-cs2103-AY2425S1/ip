package elara.utils;

import java.util.ArrayList;
import java.util.Scanner;

import elara.task.InvalidInputException;
import elara.task.Task;

/**
 * Represents the user interface of the Elara chatbot.
 * It handles input/output between the user and the system.
 */
public class Ui {
    private final Scanner scanner;
    private String lastResponse;

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
        lastResponse = "\n" + welcomeMessage + "\n";
        System.out.println(lastResponse);
    }

    /**
     * Displays the exit message when the user exits the application.
     */
    public void showExitMessage() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        lastResponse = "\n" + goodbyeMessage + "\n";
        System.out.println(lastResponse);
    }

    /**
     * Displays a message confirming that a new task has been added.
     *
     * @param task the task that has been added.
     * @param taskList the list of tasks.
     */
    public void showAddTaskMessage(Task task, TaskList taskList) {
        lastResponse = "Got it. I've added this task:" + task;
        lastResponse += "\nNow you have " + taskList.getSize() + " tasks in the list.";
        System.out.println(this.lastResponse);
    }

    /**
     * Displays the number of tasks currently in the list.
     *
     * @param taskList the list of tasks.
     */
    public void showNumOfTasksMessage(TaskList taskList) {
        lastResponse = String.format("Now you have %d tasks in the list%n", taskList.getSize());
        System.out.println(lastResponse);
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task the task that has been marked as done.
     */
    public void showMarkedTaskMessage(Task task) {
        lastResponse = "Nice! I've marked this task as done:" + task;
        System.out.println(lastResponse);
    }

    /**
     * Displays a message confirming that a task has been unmarked (not done).
     *
     * @param task the task that has been unmarked.
     */
    public void showUnmarkedTaskMessage(Task task) {
        lastResponse = "OK, I've marked this task as not done yet:" + task;
        System.out.println(lastResponse);
    }

    /**
     * Displays a message confirming that a task has been removed from the list.
     *
     * @param task the task that has been removed.
     */
    public void showRemoveTaskMessage(Task task) {
        lastResponse = "Noted. I've removed this task:" + task;
        System.out.println(lastResponse);
    }

    /**
     * Lists all tasks currently in the task list.
     *
     * @param taskList the list of tasks.
     */
    public void listTasks(TaskList taskList) {
        lastResponse = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i) == null) {
                break;
            }
            lastResponse += String.format("\n%d. %s", i + 1, taskList.getTask(i));
        }
    }

    /**
     * Displays an error message when an invalid command is entered.
     *
     * @param e the exception thrown due to an invalid command.
     */
    public void showInvalidCommandMessage(InvalidInputException e) {
        System.out.println(e.getMessage());
        lastResponse = e.getMessage();
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
        if (taskList.isEmpty()) {
            lastResponse = "No matching tasks found.";
        } else {
            lastResponse = "Here are the matching tasks in your list:";
            for (int i = 0; i < taskList.size(); i++) {
                lastResponse += String.format("\n%d. %s", i + 1, taskList.get(i));
            }
        }
        System.out.println(lastResponse);
    }

    public String getLastResponse() {
        return lastResponse;
    }

    /**
     * Displays a message when the task has already been added to the list.
     *
     * @param task the duplicate task.
     */
    public void showDuplicateMessage(Task task) {
        lastResponse = "This task has already been added to the list.";
        lastResponse += task.toString();
        System.out.println(lastResponse);
    }
}
