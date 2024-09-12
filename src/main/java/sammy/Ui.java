package sammy;

import sammy.task.Task;
import sammy.task.TaskList;

import java.util.Scanner;

/**
 * Handles interactions with the user by displaying messages and reading input.
 */
public class Ui {
    private final String line = "____________________________________________________________";

    /**
     * Displays a welcome message when the program starts.
     */
    public String showWelcomeMessage() {
        String welcomeMessage = line + "\n Hello! I'm sammy.ui.sammy.Sammy.\n" + "What can I do for you?\n" + line;
        System.out.println(line);
        System.out.println(" Hello! I'm sammy.ui.sammy.Sammy.");
        System.out.println(" What can I do for you?");
        System.out.println(line);
        return welcomeMessage;
    }

    /**
     * Displays a goodbye message when the program ends.
     */
    public String showGoodbyeMessage() {
        String goodbyeMessage = line + "\n Bye. Hope to see you again soon!\n" + line;
        System.out.println(line);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
        return goodbyeMessage;
    }

    /**
     * Displays a horizontal line used as a separator for messages.
     */
    public String showLine() {
        return line;
    }

    /**
     * Displays an error message with the specified message content.
     *
     * @param message The error message to be displayed.
     */
    public String showErrorMessage(String message) {
        String errorMessage = line + "\n OOPS!!! \n" + message;
        System.out.println(line);
        System.out.println(" OOPS!!! " + message);
        System.out.println(line);
        return errorMessage;
    }

    /**
     * Displays all the tasks in the given TaskList.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder taskList = new StringBuilder();
        taskList.append(line).append("\n");
        taskList.append(" Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }

        taskList.append(line);

        return taskList.toString();
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that has been added.
     * @param size The current number of tasks in the list.
     */
    public String showAddTask(Task task, int size) {
        StringBuilder addTask = new StringBuilder();
        addTask.append(line).append("\n");
        addTask.append(" Got it. I've added this task:\n");
        addTask.append(" ").append(task).append("\n");
        addTask.append(" Now you have ").append(size).append(" tasks in the list.\n");
        addTask.append(line);

        return addTask.toString();
    }

    /**
     * Displays a message confirming that a task has been removed.
     *
     * @param task The task that has been removed.
     * @param size The current number of tasks in the list.
     */
    public String showRemoveTask(Task task, int size) {
        StringBuilder removeTask = new StringBuilder();
        removeTask.append(line).append("\n");
        removeTask.append(" Noted. I've removed this task:\n");
        removeTask.append(" ").append(task).append("\n");
        removeTask.append(" Now you have ").append(size).append(" tasks in the list.\n");
        removeTask.append(line);

        return removeTask.toString();
    }

    public String showMarkTask(Task task) {
        StringBuilder markTask = new StringBuilder();
        markTask.append(line).append("\n");
        markTask.append(" Nice! I've marked this task as done:\n");
        markTask.append(" ").append(task).append("\n");
        markTask.append(line);
        return markTask.toString();

    }

    public String showUnmarkTask(Task task) {
        StringBuilder unmarkTask = new StringBuilder();

        unmarkTask.append(line).append("\n");
        unmarkTask.append(" OK, I've marked this task as not done yet:\n");
        unmarkTask.append(" ").append(task).append("\n");
        unmarkTask.append(line);

        return unmarkTask.toString();
    }


    public String showFindResults(TaskList tasks) {
        StringBuilder findResult = new StringBuilder();

        findResult.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            findResult.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        return findResult.toString();
    }


    /**
     * Reads and returns the next line of input from the user.
     *
     * @return The input command entered by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

