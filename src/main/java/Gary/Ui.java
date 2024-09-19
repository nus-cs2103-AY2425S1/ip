package Gary;

import java.util.Scanner;

import Gary.task.Task;

/**
 * The {@code Ui} class is responsible for interacting with the user.
 * It handles user input and output, including displaying messages and tasks.
 */
public class Ui {
    // Scanner for reading user input
    private Scanner detector;

    /**
     * Constructs a {@code Ui} object with a new {@code Scanner} instance for input.
     */
    public Ui() {
        this.detector = new Scanner(System.in);
    }

    /**
     * Displays a greeting message to the user.
     */
    public String greet() {
        return "Hello! I'm Gary\nWhat can I do for you?";
    }

    /**
     * Displays a goodbye message and closes the input scanner.
     */
    public String goodbye() {
        this.detector.close();
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The {@code Task} object that has been marked as done.
     */
    public String markTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString() + "\n";
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The {@code Task} object that has been marked as not done.
     */
    public String unmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString() + "\n";
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The {@code Task} object that has been removed.
     * @param size The new size of the task list after the task has been removed.
     */
    public String deleteTask(Task task, int size) {
        return "Noted. I've removed this task from the list:\n"
                + task.toString() + "\nNow you have " + size
                + " tasks in the list.\n";
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The {@code Task} object that has been added.
     * @param size The new size of the task list after the task has been added.
     */
    public String addTask(Task task, int size) {
        return "Got it. I've added this task:\n" + task.toString()
                + "\n Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Displays all tasks in the provided {@code TaskList}.
     *
     * @param taskList The {@code TaskList} object containing tasks to display.
     */
    public String showTaskLists(TaskList taskList) {
        String list = "";

        for (int i = 0; i < taskList.size(); i++) {
            list += i + 1 + "." + taskList.getTask(i) + "\n";
        }
        return list;
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The string input entered by the user.
     */
    public String read() {
        return detector.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public String showError(String message) {
        return message;
    }
}

