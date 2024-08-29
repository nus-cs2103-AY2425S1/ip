package bibi;

import bibi.task.Task;
import bibi.task.TaskList;
import java.util.Scanner;

/**
 * Represents an object that handles inputs and outputs to the console.
 */
public class Ui {
    Scanner s;

    /**
     * Constructs a new Ui instance.
     * Sets the scanner to react to the System.in input stream
     */
    public Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when the chat bot is launched.
     */
    public void printWelcomeMessage() {
        String logo = """
                        ########   #######   ########   #######\s
                        #       #     #      #       #     #   \s
                        ########      #      ########      #   \s
                        #       #     #      #       #     #   \s
                        #       #     #      #       #     #   \s
                        ########   #######   ########   #######\s
                      """;

        System.out.println("Hello from\n" + logo + "\n"
                + "How can I help you?");
    }

    /**
     * Returns the inputs entered into the console as a String.
     * String ends when a newline character is encountered.
     *
     * @return input as String.
     */
    public String readInput() {
        return s.nextLine();
    }

    /**
     * Prints the exit message when the "bye" command is used.
     */
    public void printExitMessage() {
        printHorizontalLine();
        System.out.println("See you soon :3");
        printHorizontalLine();
    }

    /**
     * Prints all tasks currently in the TaskList.
     *
     * @param tasks TaskList containing the tasks to be printed.
     */
    public void printListMessage(TaskList tasks) {
        printHorizontalLine();
        tasks.printTaskList();
        printHorizontalLine();
    }

    /**
     * Prints the acknowledgement message for when the "mark" command is encountered.
     *
     * @param t The Task to be marked.
     */
    public void printTaskMarkedMessage(Task t) {
        System.out.printf("Alrighty, marked the following task as done:%n");
        System.out.println(t);
    }

    /**
     * Prints the acknowledgement message for when the "unmark" command is encountered.
     *
     * @param t The Task to be unmarked.
     */
    public void printTaskUnmarkedMessage(Task t) {
        System.out.printf("Oops, we'll get 'em next time:%n");
        System.out.println(t);
    }

    /**
     * Prints the acknowledgement message for when any Task and its subclass
     * is added to the TaskList.
     *
     * @param t The Task to be added.
     * @param size The remaining size of the TaskList.
     */
    public void printTaskAddedMessage(Task t, int size) {
        System.out.printf("Added: \"%s\" to task list%n", t);
        System.out.printf("You now have %d task(s) to do%n", size);
    }

    /**
     * Prints the acknowledgement message for when any Task and its subclass
     * is removed from the TaskList.
     *
     * @param t The Task to be removed.
     * @param size The remaining size of the TaskList .
     */
    public void printTaskRemovedMessage(Task t, int size) {
        System.out.println("You will never see this task ever again >:(");
        System.out.printf("Removed %s from task list%n", t.toString());
        System.out.printf("You now have %d task(s) to do%n", size);
    }

    /**
     * Prints the warning message for when a known command is used incorrectly.
     *
     * @param message The correct command and format to use the command with.
     */
    public void printInvalidSyntaxMessage(String message) {
        System.out.printf("Invalid command syntax: %s%n", message);
    }

    /**
     * Prints the warning message for when an unknown command is detected.
     *
     * @param cmd The unknown command detected.
     */
    public void printUnknownCommandMessage(String cmd) {
        printHorizontalLine();
        System.out.printf("%s is an unknown command%n", cmd);
        printHorizontalLine();
    }

    /**
     * Prints a sequence of dashes that act as a divider
     */
    public void printHorizontalLine() {
        System.out.println("------------------------------------------------------------");
    }
}
