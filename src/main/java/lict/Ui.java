package lict;

import java.util.Scanner;

import lict.task.Task;

/**
 * The {@code Ui} class handles all interactions with the user. It provides methods for reading user input,
 * displaying output, and managing the flow of conversation with the user.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private final String tab = "    ";
    private String lastOutput = "";

    /**
     * Starts the conversation with the user by displaying a greeting message.
     */
    public void startConversation() {
        showLine();
        lastOutput = "Hello! I'm Lict!\nWhat can I do for you?";
        System.out.println(lastOutput);
        showLine();
    }

    /**
     * Reads and returns a command input by the user. Trims leading and trailing whitespace.
     *
     * @return The command input by the user.
     * @throws LictException If the input is empty.
     */
    public String readInput() throws LictException {
        String input = sc.nextLine().trim();
        if (input.isEmpty()) {
            throw new LictException("Input cannot be empty.");
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
            lastOutput = "There are no tasks in your list.";
            System.out.println(lastOutput);
        } else {
            StringBuilder listOutput = new StringBuilder();
            listOutput.append("Here are the tasks in your list:\n");
            for (int i = 0; i < size; i++) {
                listOutput.append(tab + (i + 1) + ". " + tasks.get(i) + "\n");
            }
            lastOutput = listOutput.toString();
            System.out.println(lastOutput);
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
     * @param filter The keyword to filter the tasks by. If the filter is empty, all tasks will be
     *     considered as non-matching.
     */
    public void showFilteredTasks(TaskList tasks, String filter) {
        TaskList filteredTasks = new TaskList();
        int listSize = tasks.size();
        if (listSize == 0 || filter.isEmpty()) {
            lastOutput = "There are no tasks in your list that matches the keyword '" + filter + "'.";
            System.out.println(lastOutput);
        }
        for (int j = 0; j < listSize; j++) {
            Task t = tasks.get(j);
            if (t.containsKeyword(filter)) {
                filteredTasks.addTask(t);
            }
        }
        int size = filteredTasks.size();
        if (size == 0) {
            lastOutput = "There are no tasks in your list that matches the keyword '" + filter + "'.";
            System.out.println(lastOutput);
        } else {
            StringBuilder matchingTasksOutput = new StringBuilder();
            matchingTasksOutput.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < size; i++) {
                matchingTasksOutput.append(tab + (i + 1) + ". " + filteredTasks.get(i) + "\n");
            }
            lastOutput = matchingTasksOutput.toString();
            System.out.println(lastOutput);
        }
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param t The task that has been marked as done.
     */
    public void hasMarkedTask(Task t) {
        lastOutput = "Nice! I've marked this task as done:\n" + tab + t;
        System.out.println(lastOutput);
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param t The task that has been marked as not done.
     */
    public void hasUnmarkedTask(Task t) {
        lastOutput = "OK, I've marked this task as not done yet:\n" + tab + t;
        System.out.println(lastOutput);
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param t The task that has been deleted.
     * @param numOfTasks The number of tasks remaining in the task list.
     */
    public void hasDeletedTask(Task t, int numOfTasks) {
        lastOutput = "Noted. I've removed this task:\n" + tab + t
                + "\nNow you have " + numOfTasks + " tasks in the list.";
        System.out.println(lastOutput);
    }

    /**
     * Ends the conversation with the user by displaying a goodbye message and closing the scanner.
     */
    public void endConversation() {
        sc.close();
        showLine();
        lastOutput = "Bye. Hope to see you again soon!";
        System.out.println(lastOutput);
        showLine();
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param newTask The new task that has been added.
     * @param numOfTasks The number of tasks in the task list after adding the new task.
     */
    public void hasAddedTask(Task newTask, int numOfTasks) {
        lastOutput = "Got it. I've added this task:\n" + tab + newTask
                + "\nNow you have " + numOfTasks + " tasks in the list.";
        System.out.println(lastOutput);
    }

    public String getLastOutput() {
        return lastOutput;
    }
    /**
     * Displays a message indicating that a task has been snoozed.
     *
     * @param task The task that has been snoozed with updated details.
     */
    public void hasSnoozedTask(Task task) {
        lastOutput = "Got it, I've snoozed this task. Updated task details are:\n" + tab + task;
        System.out.println(lastOutput);
    }
}
