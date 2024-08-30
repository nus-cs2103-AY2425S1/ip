package UI;

import Tasks.Task;

/**
 * Provides user interface functionalities for displaying messages related to task operations.
 */
public class Ui {

    /**
     * Prints a welcome message to the user.
     * This message introduces the application and gives a friendly greeting.
     */
    public void welcomeMessage() {
        System.out.println("Hello! I'm Delphi, the greatest oracle in all of the classical world.");
        System.out.println("For now, I will be acting as a personal assistant for you to manage your tasks:)");
    }

    /**
     * Prints a goodbye message to the user.
     * This message is displayed when the user exits the application.
     */
    public void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message indicating that a task has been added to the list.
     *
     * @param tsk The task that was added.
     * @param num The current number of tasks in the list.
     */
    public static void taskMessage(Task tsk, int num) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tsk);
        System.out.println("Now you have " + num + " tasks in the list.");
    }

    /**
     * Prints a message indicating whether a task has been marked as completed or not.
     *
     * @param completed True if the task is completed; false otherwise.
     * @param tsk The task that was marked.
     */
    public static void markingTask(boolean completed, Task tsk) {
        if (completed) {
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + tsk);
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + tsk);
        }
    }

    /**
     * Prints a message indicating that a task has been removed from the list.
     *
     * @param removedTask The task that was removed.
     * @param num The current number of tasks in the list after removal.
     */
    public static void removingTask(Task removedTask, int num) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + removedTask);
        System.out.println("    Now you have " + num + " tasks in the list");
    }

    /**
     * Prints a message indicating that matching tasks are being displayed.
     * This is typically shown when searching for tasks in the list.
     */
    public void findingTask() {
        System.out.println("    Here are the matching tasks in your list:");
    }
}

