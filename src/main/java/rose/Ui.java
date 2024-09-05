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
    public void showClosing() {
        System.out.println("    See you~~ Don't forget to drink some water ^_^");
    }

    public void showLoadingError() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    OOPS!!! There was an error loading the task list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showError(String message) {
        System.out.println("    " + message);
    }

    public void showLine() {
        System.out.println("    " + HORIZONTAL_LINE);
    }

    public void display(String message) {
        System.out.println("    " + message);
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMark(Task task) {
        this.display("Marked as done : ");
        this.display(task.toString());
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showUnmark(Task task) {
        this.display("Marked as not done : ");
        this.display(task.toString());
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current number of tasks in the list.
     */
    public void showDelete(Task task, int size) {
        this.display("Noted. I've removed this task :");
        this.display(task.toString());
        this.display(String.format("Now you have %d task in the list.", size));
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The current number of tasks in the list.
     */
    public void showAdd(Task task, int size) {
        this.display("Got it. I've added this task :");
        this.display("  " + task.toString());
        this.display(String.format("Now you have %d tasks in the list.", size));
    }

    public void showFind(ArrayList<Task> matchingTasks) {
        if (!matchingTasks.isEmpty()) {
            this.display(String.format("Here are %d tasks that matches your keyword : ", matchingTasks.size()));
            for (Task task : matchingTasks) {
                this.display(task.toString());
            }
        } else {
            this.display("There is no task that matches your keyword :(");
        }
    }

}
