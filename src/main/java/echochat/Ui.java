package echochat;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class is responsible for handling all interactions with the user.
 * It manages input and output to and from the console, including greetings,
 * task updates, and error messages.
 */
public class Ui {
    private Scanner scanner;
    private String name;

    /**
     * Constructs a Ui object with a specific name for the bot.
     *
     * @param name The name of the bot.
     */
    public Ui(String name) {
        this.scanner = new Scanner(System.in);
        this.name = name;
    }

    /**
     * Greets the user with a welcome message.
     */
    public void greet() {
        System.out.println("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }

    /**
     * Bids farewell to the user with a goodbye message.
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prompts the user for input and returns the input as a String.
     *
     * @return The input provided by the user.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays the list of tasks currently in the user's task list.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (Task task : tasks) {
            System.out.println(num + ". " + task.getDesc());
            num++;
        }
    }

    /**
     * Informs the user that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getDesc());
    }

    /**
     * Informs the user that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.getDesc());
    }

    /**
     * Informs the user that a task has been deleted and displays the updated task count.
     *
     * @param task      The task that was deleted.
     * @param taskCount The number of tasks remaining in the list.
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getDesc());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Informs the user that a task has been added and displays the updated task count.
     *
     * @param task      The task that was added.
     * @param taskCount The number of tasks in the list after adding the new task.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:" + "\n" + task.getDesc());
        if (taskCount == 1) {
            System.out.println("Now you have " + taskCount + " task in the list.");
        } else {
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    public void showFoundTasks(String description, ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in you list:");
        int idx = 0;
        for (Task task : tasks) {
            String desc = task.getDesc();
            if (desc.contains(description)) {
                System.out.println(idx + "." + desc);
            }
        }
    }
}
