package monobot.util;

import monobot.task.Task;

/**
 * Handles the user interface of the application.
 */
public class Ui {

    /**
     * Prints welcome greeting.
     */
    public void printGreeting() {
        printHorizontalLine();
        System.out.println("Hello! I'm MonoBot");
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints farewell message.
     */
    public void printFarewell() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    /**
     * Prints line break.
     */
    public void printHorizontalLine() {
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }

    /**
     * Prints message from error when loading tasks from file.
     */
    public void showLoadingError() {
        printHorizontalLine();
        System.out.println("Error loading tasks from file.");
        printHorizontalLine();
    }

    /**
     * Prints all tasks added by user.
     *
     * @param taskList taskList contains all tasks to be printed
     */
    public void printTasks(TaskList taskList) {
        printHorizontalLine();
        if (taskList.isEmpty()) {
            System.out.println("No tasks added yet");
        } else {
            System.out.println("Here are the tasks in your list");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, taskList.getTask(i));
            }
        }
        printHorizontalLine();
    }

    /**
     * Prints task that was just added by user.
     *
     * @param task task that has just been added by user
     * @param totalTasks Total number of tasks currently in the taskList
     */
    public void printAddedTask(Task task, int totalTasks) {
        printHorizontalLine();
        System.out.println("Added: " + task);
        System.out.println("Now you have " + totalTasks + " task(s) in the list");
        printHorizontalLine();
    }

    /**
     * Prints task that was just deleted by user.
     *
     * @param task task that has just been deleted by user
     * @param totalTasks Total number of tasks currently in the taskList
     */
    public void printDeletedTask(Task task, int totalTasks) {
        printHorizontalLine();
        System.out.println("Noted! I have removed this task:\n" + task);
        System.out.println("Now you have " + totalTasks + " task(s) in the list");
        printHorizontalLine();
    }

    /**
     * Prints task that was just marked by user.
     *
     * @param task task that has just been marked by user
     */
    public void printMarkedTask(Task task) {
        printHorizontalLine();
        System.out.println("Nice! I have marked this task as completed:\n" + task);
        printHorizontalLine();
    }

    /**
     * Prints task that was just unmarked by user.
     *
     * @param task task that has just been unmarked by user
     */
    public void printUnmarkedTask(Task task) {
        printHorizontalLine();
        System.out.println("Ok! I have marked this task as incomplete:\n" + task);
        printHorizontalLine();
    }

    /**
     * Prints error message.
     *
     * @param message error to be displayed
     */
    public void printError(String message) {
        printHorizontalLine();
        System.out.println(message);
        printHorizontalLine();
    }
}
