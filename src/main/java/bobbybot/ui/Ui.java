package bobbybot.ui;

import bobbybot.Task;
import bobbybot.TaskList;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Represents the user interface of BobbyBot.
 * Contains methods to interact with the user (input and output).
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The next line of input from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }


    /**
     * Prints a descriptive message, showing the task that has been removed.
     *
     * @param tasks The list of tasks.
     * @param task The task that has been removed.
     */
    public void printRemoveTask(TaskList tasks, Task task) {
        printInput(
                "Noted. I've removed this task:",
                "\t" + task,
                "Now you have " + tasks.getSize() + " task(s) in the list."
        );
    }

    /**
     * Prints a descriptive message, showing the task that has been added.
     *
     * @param tasks The list of tasks.
     * @param task The task that has been added.
     */
    public void printAddTask(TaskList tasks, Task task) {
        printInput(
                "Got it. I've added this task:",
                "\t" + task,
                "Now you have " + tasks.getSize() + " task(s) in the list."
        );
    }

    /**
     * Prints the tasks that match the search query.
     *
     * @param tasks The list of tasks that match the search query.
     */
    public void listMatchingTasks(TaskList tasks) {
        ArrayList<String> taskListString = new ArrayList<>();
        taskListString.add("Here are the matching tasks in your list:");
        IntStream.
                range(0, tasks.getSize()).
                mapToObj(i -> i + 1 + ". " + tasks.getTask(i)).
                forEach(taskListString::add);
        printInput(taskListString.toArray(String[]::new));
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param tasks The list of tasks.
     */
    public void listTasks(TaskList tasks) {
        ArrayList<String> taskListString = new ArrayList<>();
        taskListString.add("Here are the tasks in your list:");
        IntStream.
                range(0, tasks.getSize()).
                mapToObj(i -> i + 1 + ". " + tasks.getTask(i)).
                forEach(taskListString::add);
        printInput(taskListString.toArray(String[]::new));
    }

    /**
     * Prints a message that is formatted.
     *
     * @param input The message to be formatted and printed.
     */
    public void printInput(String... input) {
        System.out.print("\t");
        printLine();
        for (String i : input) {
            System.out.println("\t" + i);
        }
        System.out.print("\t");
        printLine();
    }

    /**
     * Prints an error message.
     *
     * @param e The exception that caused the error.
     */
    public void printError(Exception e) {
        printInput(e.getMessage());
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }
}
