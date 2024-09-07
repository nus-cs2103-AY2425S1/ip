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
     * Gets the welcome message at the start of the application.
     *
     * @return the welcome message as a string.
     */
    public String showWelcomeMessage() {
        return "Hello! I'm Elara\nWhat can I do for you?";
    }

    /**
     * Gets the exit message when the user exits the application.
     *
     * @return the exit message as a string.
     */
    public String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a message confirming that a new task has been added.
     *
     * @param task the task that has been added.
     * @param taskList the list of tasks.
     * @return the confirmation message as a string.
     */
    public String showAddTaskMessage(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n" + task + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.getSize());
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @param taskList the list of tasks.
     * @return the number of tasks as a string.
     */
    public String showNumOfTasksMessage(TaskList taskList) {
        return String.format("Now you have %d tasks in the list.", taskList.getSize());
    }

    /**
     * Returns a message confirming that a task has been marked as done.
     *
     * @param task the task that has been marked as done.
     * @return the confirmation message as a string.
     */
    public String showMarkedTaskMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns a message confirming that a task has been unmarked (not done).
     *
     * @param task the task that has been unmarked.
     * @return the confirmation message as a string.
     */
    public String showUnmarkedTaskMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns a message confirming that a task has been removed from the list.
     *
     * @param task the task that has been removed.
     * @return the confirmation message as a string.
     */
    public String showRemoveTaskMessage(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    /**
     * Lists all tasks currently in the task list and returns them as a string.
     *
     * @param taskList the list of tasks.
     * @return the formatted task list as a string.
     */
    public String listTasks(TaskList taskList) {
        StringBuilder tasksOutput = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            if (taskList.getTask(i) != null) {
                tasksOutput.append(i + 1).append(". ").append(taskList.getTask(i)).append("\n");
            }
        }
        return tasksOutput.toString();
    }

    /**
     * Returns an error message when an invalid command is entered.
     *
     * @param e the exception thrown due to an invalid command.
     * @return the error message as a string.
     */
    public String showInvalidCommandMessage(InvalidInputException e) {
        return e.getMessage();
    }

    /**
     * Closes the scanner used to read user input.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Returns the tasks that match the search keyword as a formatted string.
     *
     * @param taskList the list of tasks that match the search keyword.
     * @return the formatted list of matching tasks.
     */
    public String showFindTasksMessage(ArrayList<Task> taskList) {
        StringBuilder result = new StringBuilder();
        if (taskList.isEmpty()) {
            result.append("No matching tasks found.\n");
        } else {
            result.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                result.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        return result.toString();
    }
}
