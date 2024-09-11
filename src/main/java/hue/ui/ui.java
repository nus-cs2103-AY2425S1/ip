package hue.ui;

import java.util.ArrayList;
import java.util.Scanner;
import hue.task.*;


/**
 * Handles interactions with the user through the command line interface.
 */
public class ui {
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Displays a welcoming message to the user
     */
    public String showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append("Hello! I'm Hue\n");
        sb.append("What can I do for you?\n");
        sb.append("____________________________________________________________");

        return sb.toString();
    }
    /**
     * Reads a command input from the user.
     *
     * @return The command input by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public String showLine() {
        return "____________________________________________________________";
    }
    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return message;
    }
    /**
     * Displays a message indicating that the application is exiting.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The task list to be displayed.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        return response.toString();
    }

    /**
     * Finds the tasks in the ArrayList of Tasks that have descriptions which match the input keyword
     *
     * @param tasks The task list to be displayed.
     * @param keyword The keyword input by the user for the ArrayList to be filtered by
     */
    public String showMatchingTasks(ArrayList<Task> tasks, String keyword) {
        showLine();
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list that match the keyword \"").append(keyword).append("\":\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        return sb.toString().trim();  // Trim to remove any trailing newline
    }

    public String noMatchingTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("No tasks found that matches the keyword: \"").append(keyword)
                .append("\"\n")
                .append("Sowwy! :(");
        return sb.toString().trim();
    }

    /**
     * Displays a message when a task is added to the task list.
     *
     * @param task The task that was added.
     * @param taskListSize The current size of the task list.
     * @return A string representing the success message.
     */
    public String showAddTask(Task task, int taskListSize) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("  ").append(task).append("\n");
        sb.append("Now you have ").append(taskListSize).append(" task");

        // Pluralize "tasks" if necessary
        if (taskListSize != 1) {
            sb.append("s");
        }
        sb.append(" in the list!. :)");

        return sb.toString();
    }

    /**
     * Displays a message when a task is deleted from the task list.
     *
     * @param task The task that was deleted.
     * @param taskListSize The current size of the task list after deletion.
     * @return A string representing the success message.
     */
    public String showDeleteTask(Task task, int taskListSize) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append("  ").append(task).append("\n");
        sb.append("Now you have ").append(taskListSize).append(" task");

        // Pluralize "tasks" if necessary
        if (taskListSize != 1) {
            sb.append("s");
        }
        sb.append(" in the list.");

        return sb.toString();
    }

    public String showMarkTask(Task task) {
        String response = "Great job buddy! I've marked this task as done:\n" +
                "  " + task;

        return response;
    }

    public String showUnmarkTask(Task task) {
        String response = "Darn!, I've marked this task as not done yet:\n" +
                " " + task;

        return response;


    }

}

