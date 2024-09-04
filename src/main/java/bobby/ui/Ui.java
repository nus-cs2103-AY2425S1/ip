package bobby.ui;

import java.util.ArrayList;
import java.util.Scanner;

import bobby.exceptions.InvalidTaskNumberException;
import bobby.tasklist.TaskList;
import bobby.tasks.Task;



/**
 * The {@code Ui} class handles all user interactions, including displaying messages
 * to the user and reading input from the console. It serves as the interface between
 * the user and the application.
 */
public class Ui {
    private final Scanner scanner;



    /**
     * Constructs a new {@code Ui} object with a {@code Scanner} for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out the response.
     *
     * @param response the response of the chatbot.
     */
    public void showResponse(String response) {
        System.out.println(response);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void showGreeting() {
        System.out.println(getGreetingMessage());
    }

    /**
     * Returns the greeting message.
     *
     * @return the greeting message
     */
    public String getGreetingMessage() {
        return "Hello I'm Bobby\nWhat can I do for you today?";
    }

    /**
     * Returns the exit message.
     *
     * @return the exit message
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads a command input from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns a message indicating that a task has been added successfully.
     *
     * @param task the task that was added
     * @param size the current number of tasks in the list
     * @return the task added message
     */
    public String getTaskAddedMessage(Task task, int size) {
        return String.format("Task added successfully:\n  %s\nNow you have %d tasks in the list.", task, size);
    }

    /**
     * Returns a message indicating that a task has been deleted successfully.
     *
     * @param task the task that was deleted
     * @param size the current number of tasks remaining in the list
     * @return the task deleted message
     */
    public String getTaskDeletedMessage(Task task, int size) {
        return String.format("Task removed successfully:\n  %s\nNow you have %d tasks in the list.", task, size);
    }

    /**
     * Returns a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     * @return the task marked message
     */
    public String getTaskMarkedMessage(Task task) {
        return "Nice! I've marked this task as done: " + task;
    }

    /**
     * Returns a message indicating that a task has been marked as not done.
     *
     * @param task the task that was marked as not done
     * @return the task unmarked message
     */
    public String getTaskUnmarkedMessage(Task task) {
        return "OK, I've marked this task as not done yet: " + task;
    }

    /**
     * Returns a string representation of all tasks in the task list.
     *
     * @param taskList the list of tasks to be displayed
     * @return the string representation of the task list
     * @throws InvalidTaskNumberException if an invalid task number is accessed
     */
    public String getTasksList(TaskList taskList) throws InvalidTaskNumberException {
        if (taskList.isEmpty()) {
            return "No tasks added to the list yet.";
        }
        StringBuilder tasksDisplay = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            tasksDisplay.append(String.format("%d.%s\n", i + 1, taskList.get(i)));
        }
        return tasksDisplay.toString().trim();
    }

    /**
     * Returns an error message.
     *
     * @param message the error message
     * @return the formatted error message
     */
    public String getErrorMessage(String message) {
        return "OOPS!!! " + message;
    }

    /**
     * Returns a string representation of tasks that match the search criteria.
     *
     * @param tasks An ArrayList of tasks that match the search criteria.
     * @return the string representation of the found tasks
     */
    public String getFoundTasksMessage(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks found.";
        }
        StringBuilder foundTasksDisplay = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            foundTasksDisplay.append(String.format("\n%d.%s", i + 1, tasks.get(i)));
        }
        return foundTasksDisplay.toString();
    }
}
