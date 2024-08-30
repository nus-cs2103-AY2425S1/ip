package friday.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import friday.task.Task;

/**
 * The Ui class handles all interactions with the user.
 * It provides methods to display messages, read commands, and show task-related information.
 */
public class Ui {

    /**
     * Greets the user with a welcome message.
     */
    public void greet() {
        System.out.println(
                "    ____________________________________________________________\n"
                        + "     Hello! I'm Friday\n"
                        + "     What can I do for you?\n"
                        + "    ____________________________________________________________"
        );
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(
                "    ____________________________________________________________\n"
                        + "     " + message + "\n"
                        + "    ____________________________________________________________"
        );
    }

    /**
     * Reads the user's input command.
     *
     * @return The command entered by the user as a String.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays an error message indicating a loading failure.
     */
    public void showLoadingError() {
        System.out.println("Error loading file.");
    }

    /**
     * Displays a message indicating that a task has been added to the task list.
     *
     * @param task      The task that was added.
     * @param noOfTasks The current number of tasks in the list.
     */
    public void showAddedTask(Task task, int noOfTasks) {
        System.out.println(
                "    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "       " + task + "\n"
                        + "     Now you have " + noOfTasks + " tasks in the list.\n"
                        + "    ____________________________________________________________"
        );
    }

    /**
     * Displays a message indicating that a task has been deleted from the task list.
     *
     * @param task      The task that was deleted.
     * @param noOfTasks The current number of tasks remaining in the list.
     */
    public void showDeletedTask(Task task, int noOfTasks) {
        System.out.println(
                "    ____________________________________________________________\n"
                        + "     Noted. I've removed this task:\n"
                        + "       " + task + "\n"
                        + "     Now you have " + noOfTasks + " tasks in the list.\n"
                        + "    ____________________________________________________________"
        );
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println(
                "    ____________________________________________________________\n"
                        + "     Nice! I've marked this task as done:\n"
                        + "       " + task + "\n"
                        + "    ____________________________________________________________"
        );
    }

    /**
     * Displays a message indicating that a task has been unmarked as done.
     *
     * @param task The task that was unmarked as done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println(
                "    ____________________________________________________________\n"
                        + "     OK, I've marked this task as not done yet:\n"
                        + "       " + task + "\n"
                        + "    ____________________________________________________________"
        );
    }

    /**
     * Displays the list of all tasks.
     *
     * @param tasks The TaskList containing all tasks to be displayed.
     */
    public void showTasks(TaskList tasks) {
        System.out.println("    ____________________________________________________________");
        if (tasks.isTaskListEmpty()) {
            System.out.println("     Your task list is empty.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the list of tasks occurring on a specific date.
     *
     * @param tasks The TaskList containing the tasks to be displayed.
     * @param date  The date for which tasks need to be listed.
     */
    public void showSpecificTasks(TaskList tasks, LocalDate date) {
        System.out.println("    ____________________________________________________________");
        if (tasks.isTaskListEmpty()) {
            System.out.println("     No tasks found on this date.");
        } else {
            System.out.println("     Here are your tasks on "
                    + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays the list of tasks that match the search criteria.
     *
     * @param tasks The TaskList containing the matching tasks to be displayed.
     */
    public void showMatchingTasks(TaskList tasks) {
        System.out.println("    ____________________________________________________________");
        if (tasks.isTaskListEmpty()) {
            System.out.println("     No matching tasks found.");
        } else {
            System.out.println("     Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void sayGoodbye() {
        System.out.println(
                "    ____________________________________________________________\n"
                        + "     Bye. Hope to see you again soon!\n"
                        + "    ____________________________________________________________"
        );
    }
}
