package ui;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * It manages input and output, displaying messages, and reading user commands.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and logo when the application starts.
     *
     * @return The welcome message as a string.
     */
    public String showWelcome() {
        String message = "____________________________________________________________\n"
                + "Hello! I'm Jar\nWhat can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(message);
        return message;
    }

    /**
     * Displays the goodbye message when the application ends.
     *
     * @return The goodbye message as a string.
     */
    public String showGoodbye() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
        return message;
    }

    /**
     * Displays a response message in the console.
     *
     * @param response The message to be displayed.
     * @return The response message.
     */
    public String showResponse(String response) {
        System.out.println(response);
        return response;
    }

    /**
     * Displays the list of tasks in the console.
     *
     * @param taskList The string representation of the task list.
     * @return The task list as a string.
     */
    public String showTaskList(String taskList) {
        String message = "Here are the tasks in your list:\n" + taskList;
        System.out.println(message);
        return message;
    }

    /**
     * Displays the list of tasks that match a given keyword.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     * @return The matching tasks as a string.
     */
    public String showTaskList(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        if (matchingTasks.isEmpty()) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        String message = sb.toString();
        System.out.println(message);
        return message;
    }

    /**
     * Displays a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked as completed.
     * @return The task marked message as a string.
     */
    public String showTaskMarked(Task task) {
        String message = "Nice! I've marked this task as done:\n" + task;
        System.out.println(message);
        return message;
    }

    /**
     * Displays a message indicating that a task has been marked as not completed.
     *
     * @param task The task that was marked as not completed.
     * @return The task unmarked message as a string.
     */
    public String showTaskUnmarked(Task task) {
        String message = "OK, I've marked this task as not done yet:\n" + task;
        System.out.println(message);
        return message;
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param taskContent The string representation of the task that was added.
     * @return The task added message as a string.
     */
    public String showTaskAdded(String taskContent) {
        String message = "Added: " + taskContent;
        System.out.println(message);
        return message;
    }

    /**
     * Displays the current count of tasks in the list.
     *
     * @param count The number of tasks in the list.
     * @return The task count message as a string.
     */
    public String showTaskCount(int count) {
        String message = "Now you have " + count + " tasks in the list.";
        System.out.println(message);
        return message;
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @return The task deleted message as a string.
     */
    public String showDeleteTask(Task task) {
        String message = "Noted. I've removed this task:\n" + task;
        System.out.println(message);
        return message;
    }

    /**
     * Displays a message indicating that a duplicate task was found and cannot be added.
     *
     * @param task The task that is a duplicate.
     * @return The duplicate task message as a string.
     */
    public String showDuplicateMessage(Task task) {
        String message = "Duplicate found, cannot add task:\n" + task;
        System.out.println(message);
        return message;
    }

}
