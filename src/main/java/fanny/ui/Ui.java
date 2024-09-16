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
    private String message;

    /**
     * Prints a welcome message when the application starts.
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
     */
    public String printBye() {
        message = "Fanny:\nBye. Hope to see you again soon!";

        showHorizontalLine();
        System.out.println(message);
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
     */
    public String showMessage(String msg) {
        System.out.println(msg);

        return msg;
    }

    /**
     * Closes the scanner, releasing any resources associated with it.
     */
    public void close() {
        scanner.close();
    }

    public String showAddTaskMsg(Task task, TaskList list) {
        message = "Fanny:\nGot it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + list.getLength() + " tasks in the list.";

        System.out.println(message);

        return message;
    }

    public String showDeleteTaskMsg(int taskId, TaskList list) {
        message = "Fanny:\nNoted. I've removed this task:\n" + list.delete(taskId) + "\n"
                + "Now you have " + list.getLength() + " tasks in the list.";

        System.out.println(message);

        return message;
    }

    public String showMarkTaskMsg(int taskId, TaskList list) {
        message = "Fanny:\nNice! I've marked this task as done:\n"
                + list.markAsDone(taskId);

        System.out.println(message);

        return message;
    }

    public String showUnmarkTaskMsg(int taskId, TaskList list) {
        message = "Fanny:\nOK, I've marked this task as not done yet:\n"
                + list.markAsNotDone(taskId);

        System.out.println(message);

        return message;
    }

    public String showFindTaskMsg(List<Task> list) {
        if (list.isEmpty()) {
            message = "Fanny:\nNo matching tasks found.";
        } else {
            message = "Fanny:\nHere are the matching tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                message += (i + 1) + "." + list.get(i).toString() + "\n";
            }
        }

        System.out.println(message);

        return message;
    }

    public String showReminders(TaskList list) {
        List<Deadline> upcomingDeadlines = list.getUpcomingDeadlines();

        if (!upcomingDeadlines.isEmpty()) {
            message = "You have the following tasks due soon:\n";
            for (Deadline deadline : upcomingDeadlines) {
                message += deadline.toString();
            }
        } else {
            message = "Congrats, you have no upcoming deadlines!";
        }

        System.out.println(message);

        return message;
    }

}
