package bmo;
import bmo.task.Task;

import java.util.ArrayList;

public class Ui {
    public static void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints the welcome message when the program starts.
     */
    public void printWelcome() {
        printLine();
        System.out.println("Hello! I'm BMO!\nWhat can I do for you?\n");
        printLine();
    }

    /**
     * Prints the goodbye message when the program ends.
     */
    public void printGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints the message when a task is added.
     * 
     * @param task the task that was added
     * @param taskCount the number of tasks in the list
     */
    public void printTaskAdded(Task task, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    /**
     * Prints the message when a task is removed.
     * 
     * @param task the task that was removed
     * @param taskCount the number of tasks in the list
     */
    public void printTaskRemoved(Task task, int taskCount) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    /**
     * Prints the message when a task is marked as completed.
     * 
     * @param task the task that was marked as done
     */
    public void printTaskMarked(Task task) {
        printLine();
        System.out.println("OK, I've marked this task as done:");
        System.out.println("  " + task);
        printLine();
    }

    /**
     * Prints the message when a task is unmarked as incomplete.
     * @param task the task that was unmarked
     */
    public void printTaskUnmarked(Task task) {
        printLine();
        System.out.println("Nice! I've unmarked this task:");
        System.out.println("  " + task);
        printLine();
    }

    /**
     * Prints the list of tasks in the task list.
     * 
     * @param tasks the list of tasks
     */
    public void printList(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }
}