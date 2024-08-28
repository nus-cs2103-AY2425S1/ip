package bruno;

import bruno.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles all user interaction in the Bruno application.
 * It prints messages to the console and reads user input.
 */
public class Ui {
    static Scanner input = new Scanner(System.in);

    /**
     * Prints a horizontal line to the console for visual separation.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the greeting message when Bruno starts.
     */
    public static void printGreetingMessage() {
        printLine();
        System.out.println("Hello! I'm Bruno\nWhat can I do for you?");
        printLine();
    }

    /**
     * Prints the farewell message when Bruno ends.
     */
    public static void printByeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints the list of tasks stored in the application.
     *
     * @param taskList The list of tasks to be printed.
     */
    public static void printList(ArrayList<Task> taskList) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + "." + task);
        }
        printLine();
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks after adding the new task.
     */
    public static void printAddedTask(Task task, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public static void printMarkedTask(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        printLine();
    }

    /**
     * Prints a message indicating that a task has been unmarked (marked as not done).
     *
     * @param task The task that was unmarked.
     */
    public static void printUnmarkedTask(Task task) {
        printLine();
        System.out.println("Nice! I've unmarked this task as done:\n" + task);
        printLine();
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks after deletion.
     */
    public static void printDeletedTask(Task task, int taskCount) {
        printLine();
        System.out.println("Noted! I've removed this task:\n" + task);
        System.out.println("Now you have " + taskCount + " tasks in the list");
        printLine();
    }

    /**
     * Prints an error message when there is an issue loading the task file.
     */
    public static void showLoadingError() {
        printLine();
        System.out.println("There was an error loading data file. Created new task list.");
        printLine();
    }

    /**
     * Prints an error message based on the provided exception.
     *
     * @param e The exception whose message is to be printed.
     */
    public static void printErrorMessage(Exception e) {
        printLine();
        System.out.println(e.getMessage());
        printLine();
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The input provided by the user.
     */
    public static String readCommand() {
        return input.nextLine();
    }

    public static void printFoundTasks(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            System.out.println(task);
        }
        printLine();
    }

    public static void printNoTaskFound() {
        printLine();
        System.out.println("No matching tasks found");
        printLine();
    }
}
