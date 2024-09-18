package fanny.ui;

import java.util.List;
import java.util.Scanner;

import fanny.task.Deadline;
import fanny.task.Task;
import fanny.task.TaskList;

/**
 * Handles interactions with the user by printing messages and reading user input.
 */
public class Ui {

    /** Scanner object for reading user input from the console. */
    private Scanner scanner = new Scanner(System.in);
    /** The message to be displayed */
    private String message;

    /**
     * Prints a welcome message when the application starts.
     *
     * @return The hello message to be displayed.
     */
    public String printHello() {
        message = "Hello! I'm Fanny\nWhat can I do for you?";

        showHorizontalLine();
        System.out.println(message);
        showHorizontalLine();

        return message;
    }

    /**
     * Prints a goodbye message when the application ends.
     *
     * @return The goodbye message to be displayed.
     */
    public String printBye() {
        message = "Bye. Hope to see you again soon!";

        showHorizontalLine();
        showMessage(message);
        showHorizontalLine();

        return message;
    }

    /**
     * Reads and returns the user's input from the console.
     *
     * @return The user's input as a string.
     */
    public String getUserInput() {
        System.out.print("User:");
        return scanner.nextLine();
    }

    /**
     * Prints a horizontal line to the console for separating sections of output.
     */
    public void showHorizontalLine() {
        System.out.println("_____________________________________________");
    }

    /**
     * Prints a custom message to the console.
     *
     * @param msg The message to be printed.
     * @return The message to be printed.
     */
    public String showMessage(String msg) {
        System.out.println("Fanny:");
        System.out.println(msg);

        return msg;
    }

    /**
     * Closes the scanner, releasing any resources associated with it.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Prints a message when a task is added.
     *
     * @param task The task to be added.
     * @param list The list of added tasks.
     * @return The message to be displayed after adding a task.
     */
    public String showAddTaskMsg(Task task, TaskList list) {
        message = "Roger that. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + list.getLength() + " tasks in the list.";

        showMessage(message);

        return message;
    }

    /**
     * Prints a message when a task is deleted.
     *
     * @param taskId The ID of the task to be deleted.
     * @param list The list of added tasks.
     * @return The message to be displayed after deleting a task.
     */
    public String showDeleteTaskMsg(int taskId, TaskList list) {
        message = "Noted. I've removed this task:\n" + list.delete(taskId) + "\n"
                + "Now you have " + list.getLength() + " tasks in the list.";

        showMessage(message);

        return message;
    }

    /**
     * Prints a message when a task is mark as completed.
     *
     * @param taskId The ID of the task to be mark as completed.
     * @param list The list of added tasks.
     * @return The message to be displayed after marking a task as completed.
     */
    public String showMarkTaskMsg(int taskId, TaskList list) {
        message = "Awesome! I've marked this task as done:\n"
                + list.markAsDone(taskId);

        showMessage(message);

        return message;
    }

    /**
     * Prints a message when a task is mark as not completed.
     *
     * @param taskId The ID of the task to be mark as not completed.
     * @param list The list of added tasks.
     * @return The message to be displayed after marking a task as not completed.
     */
    public String showUnmarkTaskMsg(int taskId, TaskList list) {
        message = "Sadly, I've marked this task as not done yet:\n"
                + list.markAsNotDone(taskId);

        showMessage(message);

        return message;
    }

    /**
     * Prints a message when tasks matching the keyword is found.
     *
     * @param list The list of tasks that matched the keyword.
     * @return The message to display the list of matching tasks.
     */
    public String showFindTaskMsg(List<Task> list) {
        if (list.isEmpty()) {
            message = "No matching tasks found.";
        } else {
            message = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                message += (i + 1) + "." + list.get(i).toString() + "\n";
            }
        }

        showMessage(message);

        return message;
    }

    /**
     * Prints a reminder message on the tasks that are due in the next 24 hours.
     *
     * @param list The list of added tasks.
     * @return The reminder message to be displayed.
     */
    public String showReminders(TaskList list) {
        List<Deadline> upcomingDeadlines = list.getUpcomingDeadlines();

        if (!upcomingDeadlines.isEmpty()) {
            message = "Please complete these tasks soon:\n";
            for (Deadline deadline : upcomingDeadlines) {
                message += deadline.toString();
            }
        } else {
            message = "Congrats!!! You have no upcoming deadlines!";
        }

        showMessage(message);

        return message;
    }

}
