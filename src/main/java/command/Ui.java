package command;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

/**
 * Handles all user interactions for the chatbot,
 * including displaying messages and reading commands.
 */
public class Ui {
    private static final String LINE_SEPARATOR = "\t\t" + "_".repeat(50);



    /**
     * Constructs a new Ui object with a Scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public void greetUser() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tHey there! I'm ChatterBox");
        System.out.println("\t\tWhat's on your plate today?");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays the current list of tasks to the user
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tHere's what you've got on your to-do list so far:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t\t\t" + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message when task is added to the list.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\t" + task.getDescription() + " is added to your list");
        System.out.println("\t\t" + task);
        System.out.println("\t\t" + "Now you have " + taskCount + " tasks in your list.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked done.
     */
    public void showTaskMarkedAsDone(Task task) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tSuper! task.Task marked as done:");
        System.out.println("\t\t" + task);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message when task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskMarkedAsNotDone(Task task) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tSure, task has been marked as not done:");
        System.out.println("\t\t" + task);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a message when a task is removed from the list.
     *
     * @param task The task that was removed.
     * @param taskCount The current number of tasks remaining in the list.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
        System.out.println("\t\tNow you have " + taskCount + " tasks in the list");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays error message for an unknown command.
     */
    public void showErrorUnknownCommand() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays error message for an invalid task number.
     */
    public void showErrorInvalidTaskNumber() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tInvalid task number.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays error message when the description of a ToDo task is empty.
     */
    public void showErrorEmptyTodoDescription() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tOOPS!!! The description of a todo cannot be empty");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays error message when the description of a Deadline task is empty.
     */
    public void showErrorEmptyDeadlineDescription() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tOOPS!!! The description of a deadline cannot be empty");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays error message when the description of a Event task is empty.
     */
    public void showErrorEmptyEventDescription() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tOOPS!!! The description of an event cannot be empty");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tTake care! Looking forward to helping you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a custom error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\t" + message);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays the list of tasks the user wants to find.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showFindTaskList(ArrayList<Task> taskList) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("\t\tHere are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t\t\t" + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println(LINE_SEPARATOR);
    }
}

