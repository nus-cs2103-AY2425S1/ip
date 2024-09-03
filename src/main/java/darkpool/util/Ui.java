package darkpool.util;

import java.util.Scanner;

/**
 * The Ui class handles user interactions.
 * It provides methods to read commands, display greetings and goodbyes,
 * list tasks, mark and unmark tasks, delete tasks, add tasks, and show errors.
 */
public class Ui {

    final String GREETING = "\tit’s darkpool. what twisted reason dragged me into your misery?";
    final String BYE = "\tcontact me again and you'll regret it.";

    /**
     * Reads a command from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays the greeting message.
     */
    public void greeting() {
        upperLine();
        output(GREETING);
        lowerLine();
    }

    /**
     * Displays the goodbye message.
     */
    public void goodbye() {
        output(BYE);
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList the list of tasks to be displayed
     */
    public void list(TaskList taskList) {
        output(taskList.toString());
    }

    /**
     * Displays a message indicating a task has been marked.
     *
     * @param task the task that has been marked
     */
    public void mark(String task) {
        output("\twhy do i have to mark this mess\n\t\t" + task);
    }

    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param task the task that has been unmarked
     */
    public void unmark(String task) {
        output("\twhy do i have to unmark this mess\n\t\t" + task);
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task the task that has been deleted
     */
    public void delete(String task) {
        output("\twhy do i have to delete this mess\n\t\t" + task);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task the task that has been added
     * @param size the new size of the task list
     */
    public void add(String task, int size) {
        output("\ti have dumped this nonsense on the list\n\t\t" + task + "\n\tnow you are stuck with " + size + " goddamn tasks");
    }

    /**
     * Outputs the specified message in red color.
     *
     * @param input the message to be output
     */
    public void output(String input) {
        System.out.println("\u001B[31m" + input + "\u001B[0m");
    }

    /**
     * Displays an error message in cyan color.
     *
     * @param input the error message to be displayed
     */
    public void showError(String input) {
        System.out.println("\t\u001B[36m" + input + "\u001B[0m");
    }

    /**
     * Displays the upper line of the UI.
     */
    public void upperLine() {
        System.out.println("\u001B[34m\t—————————————————————————————————————————————————————————————————\t\u001B[0m");
    }

    /**
     * Displays the lower line of the UI.
     */
    public void lowerLine() {
        System.out.println("\u001B[34m\t—————————————————————————————————————————————————————————————————\u001B[0m");
    }
}