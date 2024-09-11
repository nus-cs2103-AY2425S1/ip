package skd.ui;

import java.util.List;
import java.util.Scanner;

import task.Task;

/**
 * Handles the interactions with the user in the SKD application.
 *
 * The Ui class is responsible for displaying messages to the user and receiving input from the user.
 * It facilitates communication between the user and the application, such as showing task lists,
 * prompting for input, and displaying error or success messages.
 */
public class Ui {
    private static final String LINE = "    _______________________________________";
    private Scanner scanner;

    /**
     * Creates Ui instance and initializes the Scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays line separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Returns a line separator as a string.
     *
     * @return A string representing a line separator.
     */
    public String stringShowLine() {
        return LINE;
    }


    /**
     * Displays welcome message when the chatbot starts.
     */
    public void showWelcome() {
        showLine();
        System.out.println("     Hello! I'm skd.SKD");
        System.out.println("     What can I do for you?");
        showLine();
    }

    /**
     * Returns a welcome message when the chatbot starts.
     *
     * @return The welcome message as a string.
     */
    public String stringShowWelcome() {
        String s = stringShowLine() + "\n     Hello! I'm SkD\n     What can I do for you?\n" + stringShowLine();
        return s;
    }

    /**
     * Reads command from the user input.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays bye message when the chatbot exits.
     */
    public void showByeMessage() {
        showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Returns the bye message when the chatbot exits.
     *
     * @return The bye message as a string.
     */
    public String stringShowByeMessage() {
        String s = stringShowLine() + "\n     Bye. Hope to see you again soon!\n"
                + stringShowLine();
        return s;
    }

    /**
     * Displays current list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        showLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /**
     * Returns the current list of tasks as a string.
     *
     * @param tasks The list of tasks to display.
     * @return A string representing the list of tasks.
     */
    public String stringShowTaskList(List<Task> tasks) {
        StringBuilder taskList = new StringBuilder();
        taskList.append(stringShowLine()).append("\n     Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append("     ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        taskList.append(stringShowLine());
        return taskList.toString();
    }

    /**
     * Displays message showing task has been added.
     *
     * @param task The task that was added.
     * @param taskCount Current number of tasks.
     */
    public void showAddedTask(Task task, int taskCount) {
        showLine();
        task.printTaskAddedMessage(taskCount);
        showLine();
    }

    /**
     * Returns a message showing that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount Current number of tasks.
     * @return A string representing the added task message.
     */
    public String stringShowAddedTask(Task task, int taskCount) {
        return stringShowLine() + "\n" + task.stringPrintTaskAddedMessage(taskCount) + "\n" + stringShowLine();
    }

    /**
     * Displays error message.
     *
     * @param message Error message to display.
     */
    public void showError(String message) {
        showLine();
        System.out.println("     " + message);
        showLine();
    }

    /**
     * Returns an error message.
     *
     * @param message Error message to display.
     * @return A string representing the error message.
     */
    public String stringShowError(String message) {
        return stringShowLine() + "\n     " + message + "\n" + stringShowLine();
    }

    /**
     * Displays tasks that match given keyword.
     *
     * @param tasks List of tasks to search .
     * @param keyword Keyword to search for in task descriptions.
     */
    public void showFoundTasks(List<Task> tasks, String keyword) {
        showLine();
        System.out.println("     Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                System.out.println("     " + (count++) + "." + task);
            }
        }
        if (count == 1) {
            System.out.println("     No matching tasks found.");
        }
        showLine();
    }

    /**
     * Returns tasks that match the given keyword as a string.
     *
     * @param tasks List of tasks to search.
     * @param keyword Keyword to search for in task descriptions.
     * @return A string representing the tasks found.
     */
    public String stringShowFoundTasks(List<Task> tasks, String keyword) {
        StringBuilder foundTasks = new StringBuilder();
        foundTasks.append(stringShowLine()).append("\n     Here are the matching tasks in your list:\n");
        int count = 1;
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.append("     ").append(count++).append(".").append(task).append("\n");
            }
        }
        if (count == 1) {
            foundTasks.append("     No matching tasks found.\n");
        }
        foundTasks.append(stringShowLine());
        return foundTasks.toString();
    }
}
