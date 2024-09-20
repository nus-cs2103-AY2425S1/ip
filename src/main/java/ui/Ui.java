package ui;

import java.util.Scanner;

import task.Task;
import task.TaskList;

/**
 * Represents the user interface of the chatbot.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Buddy\nWhat can I do for you?";
    }

    /**
     * Returns the goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns an error message when loading tasks from file fails.
     */
    public String showLoadingError() {
        return "Failed to load tasks from file!";
    }

    /**
     * Returns an error message when saving tasks to file fails.
     */
    public String showSavingError() {
        return "Failed to save tasks to file!";
    }

    /**
     * Returns a message when a task is added.
     */
    public String showTaskAdded(Task task, int size) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message when a task is marked as done.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Returns a message when a task is unmarked.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns a message when a task is deleted.
     */
    public String showTaskDeleted(Task task, int size) {
        return "Noted. I've removed this task:\n  " + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message when a task list is shown.
     */
    public String showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "You have no tasks in your list!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(".").append(taskList.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a message when a task is found.
     */
    public String showFoundTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "No matching tasks found!";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(".").append(taskList.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Reads the next command from the user.
     *
     * @return The next command from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
