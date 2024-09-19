package arsenbot.ui;

import arsenbot.task.Task;
import arsenbot.task.TaskList;

import java.util.List;
import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It reads user input, displays messages, and shows task-related feedback.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs an Ui object and initializes a Scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public String showWelcome() {
        System.out.println("Hello! I'm ArsenBot");
        System.out.println("What can I do for you?\n");
        return """
                Hello! I'm ArsenBot
                What can I do for you?
                """;
    }

    /**
     * Displays a separator line for better readability in the output.
     */
    public void showLine() {
        System.out.println("____________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public String showError(String message) {
        assert message != null : "Error message should not be null";
        System.out.println(message);
        return message;
    }

    /**
     * Reads the next user command from input.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        String command = scanner.nextLine();
        assert command != null : "Command input should not be null";
        assert !command.trim().isEmpty() : "Command input should not be empty";
        return command;
    }

    /**
     * Displays the exit message when the application is about to close.
     */
    public String showExit() {
        System.out.println("Bye. Hope to see you again soon!\n");
        return "Bye. Hope to see you again soon! (please quit the program manually!)";
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task the task that was added
     * @param size the total number of tasks in the list after adding the new task
     */
    public String showTaskAdded(Task task, int size) {
        assert task != null : "Added task should not be null";
        assert size >= 0 : "Task list size should not be negative";
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        return "Got it. I've added this task:\n" + "\t" + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task the task that was deleted
     * @param size the total number of tasks remaining in the list after deletion
     */
    public String showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
        return "Noted. I've removed this task:\n" + task + "\n" + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public String showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task the task that was marked as not done
     */
    public String showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Displays the list of tasks currently in the task list.
     *
     * @param tasks the TaskList containing all the tasks to be displayed
     */
    public String showTaskList(TaskList tasks) {
        assert tasks != null : "Task list should not be null";
        assert tasks.size() > 0 : "Task list should contain at least one task to display";
        System.out.println("Here are the tasks in your list:");
        StringBuilder ret = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
            ret.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return ret.toString();
    }

    public String showFoundTasks(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
            return "No matching tasks found.\n";
        } else {
            System.out.println("Here are the matching tasks in your list:");
            StringBuilder ret = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + ". " + foundTasks.get((i)));
                ret.append((i + 1)).append(". ").append(foundTasks.get((i))).append("\n");
            }
            return ret.toString();
        }
    }
}
