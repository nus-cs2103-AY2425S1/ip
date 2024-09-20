package bellroy.GUI;

import bellroy.task.Task;
import bellroy.task.TaskList;

import java.util.Scanner;

/**
 * The Ui class handles reading inputs from the user and printing the output to the console
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Asks the user for the next action to perform
     * @return input from the user
     */
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * prints the initial message to the user.
     * @return the message to be printed
     */
    public static String welcomeMessage() {
        return ("Hoot Hoot! I'm Bellroy, your task manager!\n" +
                "What can I do for you? Hoot Hoot!\n");
    }

    /**
     * prints the farewell message when user closes the Chatbot
     * @return the message to be printed
     */
    public static String byeMessage() {
        return ("Hootbye! Hoot to see you again soon!\n");
    }

    /**
     * prints a message to show that the task has been added
     * @param task to be added to the list
     * @param size number of tasks after adding this task
     * @return the message to be printed
     */
    public static String taskAddedMessage(Task task, int size) {
        String s = String.format("Hoot Hoot! I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.\n",task, size);
        return s;
    }

    /**
     * Displays all tasks to the user in the form of a list
     * @param taskList all tasks in List form
     * @return the message to be printed
     */
    public static String printTaskList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * prints out message to show that the task is marked as done
     * @param task to mark as done
     * @return the message to be printed
     */
    public static String markedDone(Task task) {
        return ("Nice! I've marked this task as done:\n" + task.toString() + "\n" + "Hoot Hoot!\n" );
    }

    /**
     *  prints out message to show that the task is marked as undone
     * @param task to mark as undone
     * @return the message to be printed
     */
    public static String markedUndone(Task task) {
        return ("Hoot Hoot! I've marked this task as not done yet:\n" + task.toString() + "\n");
    }

    /**
     * prints out message to show that the task is deleted
     * @param task to be deleted
     * @param size number of tasks remaining after deletion
     * @return the message to be printed
     */
    public static String taskDeleted(Task task, int size) {
        String s = String.format("Hoot Hoot! I've removed this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.\n",task, size);
        return s;
    }

    /**
     * prints out the message to show that the association is added to the task
     * @param task the task to add the association
     * @param association the association to be added
     * @return the message to be printed
     */
    public static String associationMessage(Task task, String association) {
        return ("Hoot Hoot! I've tagged this task:\n " + task + " \nwith association: " + association);
    }

    /**
     * prints out the tasks that have been found by the find method
     * @param matchingTask the tasklist that contains the tasks that have been found
     * @return the message to be printed
     */
    public static String findTask(TaskList matchingTask) {
        if (matchingTask.isEmpty()) {
            return ("Hoot Hoot! There are no matching tasks");
        } else {
            String s = ("Hoot Hoot! Here are the matching tasks in your list:\n" + matchingTask + "\n");
            return s;
        }
    }

    /**
     * prints out the tasks with the given association
     * @param filteredTask the list of tasks with the association
     * @return the message to be printed
     */
    public static String filterTask(TaskList filteredTask) {
        if (filteredTask.isEmpty()) {
            return ("Hoot Hoot! There are no matching tasks");
        } else {
            String s = ("Hoot Hoot! Here are the filtered tasks in your list:\n" + filteredTask + "\n");
            return s;
        }
    }

    /**
     * Error message if the input due date is in the wrong format
     * @return the message to be printed
     */
    public static String deadlineDateError() {
        return "Hoot! Please input a valid date-time format";
    }

    /**
     * Error message if the input does not contain the /by to split the task
     * @return the message to be printed
     */
    public static String deadlineFormatError() {
        return "Hoot! Please ensure your format is correct!\n" + "e.g deadline (description) /by (dueDate)\n";
    }

    /**
     * Error message if the input does not contain the /from and /to for the event task
     * @return the message to be printed
     */
    public static String eventFormatError() {
        return "Hoot! Please ensure your format is correct!\n" + "e.g. event (description) /from (start) /to (end)\n";
    }

}
