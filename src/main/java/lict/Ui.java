package lict;

import lict.task.Task;
import java.util.Scanner;

/**
 * The {@code Ui} class handles all interactions with the user. It provides methods for reading user input,
 * displaying output, and managing the flow of conversation with the user.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Starts the conversation with the user by displaying a greeting message.
     */
    public void startConversation() {
        showLine();
        System.out.println("Hello! I'm Lict");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Reads and returns a command input by the user. Trims leading and trailing whitespace.
     *
     * @return The command input by the user.
     * @throws LictException If the input is empty.
     */
    public String readCommand() throws LictException {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            throw new LictException("Please enter a valid command.");
        }
        return input;
    }

    /**
     * Displays a separator line for clarity in the console output.
     */
    public void showLine() {
        System.out.println("__________________________________");
    }

    /**
     * Displays an error message in the console.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays the list of tasks currently in the task list.
     *
     * @param tasks The task list containing the tasks to display.
     */
    public void showTaskList(TaskList tasks) {
        int size = tasks.size();
        if (size == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Displays a list of tasks that match the given keyword filter.
     * <p>
     * This method searches through the provided task list for tasks that contain the specified keyword
     * and displays them. If no tasks match the keyword or if the keyword is empty, a message indicating
     * that no matching tasks were found will be displayed.
     * </p>
     *
     * @param tasks  The list of tasks to be searched and filtered.
     * @param filter The keyword to filter the tasks by. If the filter is empty, all tasks will be considered as non-matching.
     */
    public void showFilteredTasks(TaskList tasks, String filter) {
        TaskList filteredTasks = new TaskList();
        int listSize = tasks.size();
        if (listSize == 0 || filter.isEmpty()) {
            System.out.println("There are no tasks in your list that matches the keyword '" + filter + "'.");
        }
        for (int j = 0; j < listSize; j++) {
            Task t = tasks.get(j);
            if (t.containsKeyword(filter)) {
                filteredTasks.addTask(t);
            }
        }
        int size = filteredTasks.size();
        if (size == 0) {
            System.out.println("There are no tasks in your list that matches the keyword '" + filter + "'.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < size; i++) {
                System.out.println((i + 1) + ". " + filteredTasks.get(i));
            }
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param t The task that has been marked as done.
     */
    public void hasMarkedTask(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + t);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param t The task that has been marked as not done.
     */
    public void hasUnmarkedTask(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("    " + t);
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param t The task that has been deleted.
     * @param numOfTasks The number of tasks remaining in the task list.
     */
    public void hasDeletedTask(Task t, int numOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + t);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Ends the conversation with the user by displaying a goodbye message and closing the scanner.
     */
    public void endConversation() {
        sc.close();
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param newTask The new task that has been added.
     * @param numOfTasks The number of tasks in the task list after adding the new task.
     */
    public void hasAddedTask(Task newTask, int numOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + newTask);
        System.out.println("Now you have " + numOfTasks + " in the list.");
    }
}
