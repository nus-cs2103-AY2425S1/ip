package max.main;

import max.task.Task;

import java.util.ArrayList;

/**
 * The Ui class handles all user interaction, printing messages and task information
 * to the console. It provides methods for printing tasks, messages, and program-specific
 * greetings and farewells.
 */
public class Ui {

    /**
     * Prints a message indicating that a task has been added, along with the current number
     * of tasks in the list.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void printTaskTypeAdded(Task task, int size) {
        printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + task.toString());
        System.out.println("\t Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message to the console.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println("\t " + message);
        printLine();
    }

    /**
     * Prints a welcome message when the program starts.
     */
    public void printHello() {
        printLine();
        printMessage("Hello! I'm Max\n\t What can I do for you?");
    }

    /**
     * Prints a goodbye message when the program ends.
     */
    public void printBye() {
        printLine();
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a horizontal line to separate different sections of output.
     */
    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the list of tasks in the current task list.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void list(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            System.out.println("\t " + count + "." + tasks.get(i).toString());
        }
        printLine();
    }

    /**
     * Prints the list of tasks to the console.
     * <p>
     * Displays a different message based on whether the tasks are filtered or not.
     * If {@code isFilter} is {@code true}, the message will indicate that only matching tasks are shown.
     * If {@code isFilter} is {@code false}, the message will indicate that all tasks are displayed.
     * </p>
     *
     * @param isFilter A boolean indicating whether the tasks are filtered.
     *                 {@code true} if only matching tasks are displayed,
     *                 {@code false} if all tasks are displayed.
     */
    public void printList(boolean isFilter) {
        printLine();
        if (isFilter) {
            System.out.println("\t Here are the matching tasks in your list:");
        } else {
            System.out.println("\t Here are the tasks in your list:");
        }
    }

    /**
     * Prints a message indicating that a task has been deleted, along with the current number
     * of tasks remaining in the list.
     *
     * @param removedTask The task that was removed.
     * @param size The current number of tasks in the list.
     */
    public void printDeleteTask(Task removedTask, int size) {
        printLine();
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + removedTask.toString());
        System.out.println("\t Now you have " + size + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkDone(Task task) {
        printLine();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + task.toString());
        printLine();
    }

    /**
     * Prints a message indicating that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printMarkNotDone(Task task) {
        printLine();
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + task.toString());
        printLine();
    }
}
