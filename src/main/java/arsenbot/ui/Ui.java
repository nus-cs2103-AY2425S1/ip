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
        System.out.println("Hello! I am ArsenBot. It is my pleasure to assist you.");
        System.out.println("How may I serve you today?\n");
        return """
                Hello! I am ArsenBot. It is my pleasure to assist you.
                How may I serve you today?
                """;
    }

    /**
     * Displays a separator line for better readability in the output.
     */
    public void showLine() {
        System.out.println("_____________________________________________");
    }

    /**
     * Displays an error message to the user in a polite and respectful manner.
     *
     * @param message the error message to display
     */
    public String showError(String message) {
        assert message != null : "Error message should not be null";
        System.out.println("I apologize. There seems to be an issue: " + message);
        return "I apologize. There seems to be an issue: " + message;
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
        System.out.println("Goodbye. I hope to be of service again soon!\n");
        return "Goodbye. I hope to be of service again soon!";
    }

    /**
     * Displays a message indicating that a task has been added, using polite phrasing.
     *
     * @param task the task that was added
     * @param size the total number of tasks in the list after adding the new task
     */
    public String showTaskAdded(Task task, int size) {
        assert task != null : "Added task should not be null";
        assert size >= 0 : "Task list size should not be negative";
        System.out.println("Understood. I have successfully added this task:");
        System.out.println("\t" + task);
        System.out.println("You now have " + size + " tasks in your list.");
        return "Understood. I have successfully added this task:\n" + "\t" + task + "\n" + "You now have " + size + " tasks in your list.";
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task the task that was deleted
     * @param size the total number of tasks remaining in the list after deletion
     */
    public String showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I have removed this task as requested:");
        System.out.println(task);
        System.out.println("You now have " + size + " tasks remaining.");
        return "Noted. I have removed this task as requested:\n" + task + "\n" + "You now have " + size + " tasks remaining.";
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task the task that was marked as done
     */
    public String showTaskMarked(Task task) {
        System.out.println("Well done! I have marked this task as completed:");
        System.out.println(task);
        return "Well done! I have marked this task as completed:\n" + task;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param task the task that was marked as not done
     */
    public String showTaskUnmarked(Task task) {
        System.out.println("Understood. I have marked this task as not completed yet:");
        System.out.println(task);
        return "Understood. I have marked this task as not completed yet:\n" + task;
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
            System.out.println("I am sorry, no matching tasks were found.");
            return "I am sorry, no matching tasks were found.\n";
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
