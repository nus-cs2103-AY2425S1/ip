package seedu.avo.ui;

import java.util.List;
import java.util.Scanner;

import seedu.avo.tasks.Task;

/**
 * Represents an interface for user interactions
 */
public class AppUI {
    private final Scanner scanner;
    public AppUI() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message
     */
    public void showWelcome() {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    /**
     * Displays an exit message
     */
    public void showExit() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    /**
     * Displays an error message
     */
    public void showError(String message) {
        System.out.println(message);
    }
    /**
     * Reads user input from CLI
     * @return A raw input from the user
     */
    public String readInput() {
        String input = "exit";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }
    private void print(String str) {
        System.out.println("         " + str);
    }
    private String getTask(List<Task> tasks, int index) {
        return index + 1 + ". " + tasks.get(index);
    }

    /**
     * Displays the number of tasks with a user-friendly message
     * @param count The number of tasks
     */
    public String printTaskCount(int count) {
        if (count == 0) {
            return "You have no tasks.";
        } else if (count == 1) {
            return "You have one task.";
        } else {
            return String.format("You have %s tasks.", count);
        }
    }

    /**
     * Displays the list of tasks with the given indices in a user-friendly manner
     * @param tasks The list of tasks
     * @param indexes The list of indices that will be printed out
     */
    public String printTasksFromList(List<Task> tasks, List<Integer> indexes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer index: indexes) {
            stringBuilder.append(getTask(tasks, index)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Displays a user-friendly message when a task is marked as completed
     * @param tasks The list of tasks
     * @param index The index of the completed task
     */
    public String printTaskMarked(List<Task> tasks, int index) {
        return "Nice! I've marked this task as done:\n"
                + getTask(tasks, index);
    }
    /**
     * Displays a user-friendly message when a task is marked as uncompleted
     * @param tasks The list of tasks
     * @param index The index of the uncompleted task
     */
    public String printTaskUnmarked(List<Task> tasks, int index) {
        return "OK, I've marked this task as not done yet:\n"
                + getTask(tasks, index);
    }

    /**
     * Displays a user-friendly message when a task is added
     * @param tasks The list of tasks
     */
    public String printTaskAdded(List<Task> tasks) {
        return "Got it. I've added this task:\n"
                + getTask(tasks, tasks.size() - 1);
    }
    /**
     * Displays a user-friendly message when a task is removed
     * @param task The removed task
     */
    public String printTaskRemoved(Task task) {
        return "Noted. I've removed this task:\n"
                + task.toString();
    }
}
