package tudee.ui;

import tudee.task.Task;

import java.util.Scanner;
import java.util.List;

/**
 * The Ui class handles the interaction with the user.
 * It manages input and output, displaying messages to the user,
 */
public class Ui {
    private final Scanner sc;
    private static final String STRAIGHTLINE = "____________________________________________________________ \n";

    /**
     * Constructs a new Ui object and initializes the scanner
     * for reading user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a straight line to the console to separate sections of output.
     */
    public void showLine() {
        System.out.println(STRAIGHTLINE);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Tudee, your chatbot bestie! \nHow can I help you today? :) \n");
        showLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon! Don't forget about me :( \n");
        showLine();
    }

    /**
     * Displays the current list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(List<Task> tasks) {
        showLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */
    public void showMark(Task task) {
        showLine();
        System.out.println("Nice! I've marked this task as done: \n" + task);
        showLine();
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnMark(Task task) {
        showLine();
        System.out.println("Ok, I've marked this task as not done yet: \n" + task);
        showLine();
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task  The task that has been added.
     * @param count The total number of tasks in the list after adding the new task.
     */
    public void showAdd(Task task, int count) {
        showLine();
        System.out.println("Got it. I've added this task: \n  " + task);
        System.out.println("Now you have " + count + " tasks in the list. \n");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task  The task that has been deleted.
     * @param count The total number of tasks in the list after deleting the task.
     */
    public void showDelete(Task task, int count) {
        showLine();
        System.out.println("Noted. I've removed this task: \n" + task);
        System.out.println("Now you have " + count + " tasks in the list. \n");
        showLine();
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user as a string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Displays the task name to the user.
     * @param task The task we want to be displayed.
     */
    public void showTask(Task task) {
        System.out.println(task);
    }

    public void close() {
        if (sc != null) {
            sc.close();
        }
    }
}