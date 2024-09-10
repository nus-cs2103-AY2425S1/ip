package fanny.ui;

import fanny.task.Deadline;
import fanny.task.Task;
import fanny.task.TaskList;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Handles interactions with the user by printing messages and reading user input.
 */
public class Ui {

    /** Scanner object for reading user input from the console. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints a welcome message when the application starts.
     */
    public void printHello() {
        showHorizontalLine();
        System.out.println("Hello! I'm Fanny\nWhat can I do for you?");
        showHorizontalLine();
    }

    /**
     * Prints a goodbye message when the application ends.
     */
    public void printBye() {
        showHorizontalLine();
        System.out.println("Fanny:\nBye. Hope to see you again soon!");
        showHorizontalLine();
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
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner, releasing any resources associated with it.
     */
    public void close() {
        scanner.close();
    }

    public void showAddTaskMsg(Task task, TaskList list) {
        System.out.println("Fanny:\nGot it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + list.getLength() + " tasks in the list.");
    }

    public void showDeleteTaskMsg(int taskId, TaskList list) {
        System.out.println("Fanny:\nNoted. I've removed this task:");
        System.out.println(list.delete(taskId));
        System.out.println("Now you have " + list.getLength() + " tasks in the list.");
    }

    public void showMarkTaskMsg(int taskId, TaskList list) {
        System.out.println("Fanny:\nNice! I've marked this task as done:");
        System.out.println(list.markAsDone(taskId));
    }

    public void showUnmarkTaskMsg(int taskId, TaskList list) {
        System.out.println("Fanny:\nOK, I've marked this task as not done yet:");
        System.out.println(list.markAsNotDone(taskId));
    }

    public void showFindTaskMsg(List<Task> list) {
        if (list.isEmpty()) {
            System.out.println("Fanny:\nNo matching tasks found.");
        } else {
            System.out.println("Fanny:\nHere are the matching tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i).toString());
            }
        }
    }

    public void showReminders(TaskList list) {
        List<Deadline> upcomingDeadlines = list.getUpcomingDeadlines();

        if (!upcomingDeadlines.isEmpty()) {
            System.out.println("You have the following tasks due soon:");
            for (Deadline deadline : upcomingDeadlines) {
                System.out.println(deadline.toString());
            }
        } else {
            System.out.println("Congrats, you have no upcoming deadlines!");
        }
    }

}
