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
    public String greet() {
        String greetMessage = "Hello! I'm " + this.name + "\nWhat can I do for you?";
        System.out.println(greetMessage);
        return greetMessage;
    }

    /**
     * Bids farewell to the user with a goodbye message.
     */
    public String goodbye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
        return goodbyeMessage;
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
    public String showTaskList(ArrayList<Task> tasks) {
        String outputMessage = "Here are the tasks in your list:";
        int num = 1;
        for (Task task : tasks) {
            outputMessage += ("\n" + num + ". " + task.getDesc());
            num++;
        }
        System.out.println(outputMessage);
        return outputMessage;
    }

    /**
     * Informs the user that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public String showTaskMarked(Task task) {
        String outputMessage = ("Nice! I've marked this task as done:" + "\n" + task.getDesc());
        System.out.println(outputMessage);
        return outputMessage;
    }

    /**
     * Informs the user that a task has been marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public String showTaskUnmarked(Task task) {
        String outputMessage = ("OK, I've marked this task as not done yet:" + "\n" + task.getDesc());
        System.out.println(outputMessage);
        return outputMessage;
    }

    /**
     * Informs the user that a task has been deleted and displays the updated task count.
     *
     * @param task      The task that was deleted.
     * @param taskCount The number of tasks remaining in the list.
     */
    public String showTaskDeleted(Task task, int taskCount) {
        String outputMessage = ("Noted. I've removed this task:" + "\n" + task.getDesc() +
                "\n" + "Now you have " + taskCount + " tasks in the list.");
        System.out.println(outputMessage);
        return outputMessage;

    }

    /**
     * Informs the user that a task has been added and displays the updated task count.
     *
     * @param task      The task that was added.
     * @param taskCount The number of tasks in the list after adding the new task.
     */
    public String showTaskAdded(Task task, int taskCount) {
        String outputMessage = ("Got it. I've added this task:" + "\n" + task.getDesc());
        if (taskCount == 1) {
            outputMessage += ("\n" + "Now you have " + taskCount + " task in the list.");
        } else {
            outputMessage += ("\n" + "Now you have " + taskCount + " tasks in the list.");
        }
        System.out.println(outputMessage);
        return outputMessage;
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        System.out.println(message);
        return message;
    }

    /**
     * Shows the tasks that have been found based on search keyword(s).
     *
     * @param description Keywords to search for.
     * @param tasks Tasks to be searched.
     * @return Tasks that contain keyword.
     */
    public String showFoundTasks(String description, ArrayList<Task> tasks) {
        String outputMessage = ("Here are the matching tasks in you list:");
        int idx = 0;
        for (Task task : tasks) {
            String desc = task.getDesc();
            if (desc.contains(description)) {
                outputMessage += ("\n"+ idx + "." + desc);
            }
        }
        System.out.println(outputMessage);
        return outputMessage;
    }

    public String showDuplicate() {
        return "Task already exists. Skipped task.";
    }
}
