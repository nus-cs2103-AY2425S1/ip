package rose;

import java.util.ArrayList;
import java.util.Scanner;

import rose.task.Task;

/**
 * The {@code Ui} class handles interactions with the user through the command line interface.
 *
 * <p>This class provides methods to display various messages and prompts, such as greetings,
 * task updates, and error messages. It also reads user input commands.</p>
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String NAME = "Rose";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the opening message when the application starts.
     */
    public void showOpening() {
        System.out.println("    " + HORIZONTAL_LINE + "\n    Hi gorgeous! I'm " + NAME + "~~\n"
                + "    How can I help you today?\n    " + HORIZONTAL_LINE);
    }

    /**
     * Displays the closing message when the application exits.
     */
    public String showClosing() {
        return "See you~~ Don't forget to drink some water ^_^";
    }

    public void showLoadingError() {
        System.out.println("OOPS!!! Your task list might not be accurate because "
                + "there was an error loading the task list.");
    }

    public String showError(String message) {
        return message;
    }

    public void showLine() {
        System.out.println("    " + HORIZONTAL_LINE);
    }


    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @return A string indicating the marked task.
     * @param task The task that was marked as done.
     */
    public String showMark(Task task) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Marked as done : \n");
        outputString.append(task.toString());
        return String.valueOf(outputString);
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @return A string indicating the unmarked task.
     * @param task The task that was marked as not done.
     */
    public String showUnmark(Task task) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Marked as not done : \n");
        outputString.append(task.toString());
        return String.valueOf(outputString);
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     * @return A string indicating the deleted task.
     */
    public String showDelete(Task task, int size) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Noted. I've removed this task :\n");
        outputString.append(task.toString());
        outputString.append(String.format("\nNow you have %d task in the list.", size));
        return String.valueOf(outputString);
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     * @return A string indicating the added task.
     */
    public String showAdd(Task task, int size) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Got it. I've added this task :\n    ");
        outputString.append(task.toString());
        outputString.append(String.format("\nNow you have %d tasks in the list.", size));
        return String.valueOf(outputString);
    }

    /**
     * Displays a message indicating tasks that matches the keyword given.
     *
     * @param matchingTasks List of task that matches the keyword.
     * @return A string showing which task(s) matches the keyword.
     */
    public String showFind(ArrayList<Task> matchingTasks) {
        StringBuilder outputString = new StringBuilder();
        if (!matchingTasks.isEmpty()) {
            outputString.append(String.format("Here are %d tasks that matches your keyword : \n", matchingTasks.size()));
            for (Task task : matchingTasks) {
                outputString.append(task.toString());
                outputString.append("\n");
            }
        } else {
            outputString.append("There is no task that matches your keyword :(");
        }
        return String.valueOf(outputString);
    }

}
