package Bellroy.GUI;

import Bellroy.task.Task;
import Bellroy.task.TaskList;

import java.util.List;
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
     */
    public static String welcomeMessage() {
        return ("Hoot Hoot! I'm Bellroy, your task manager!\n" +
                "What can I do for you? Hoot Hoot!\n");
    }

    /**
     * prints the farewell message when user closes the Chatbot
     */
    public static String byeMessage() {
        return ("Hootbye! Hoot to see you again soon!\n");
    }

    /**
     * prints a message to show that the task has been added
     * @param task to be added to the list
     * @param size number of tasks after adding this task
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
     */
    public static String printTaskList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * prints out message to show that the task is marked as done
     * @param task to mark as done
     */
    public static String markedDone(Task task) {
        return ("Nice! I've marked this task as done:\n" + task.toString() + "\n" + "Hoot Hoot!\n" );
    }

    /**
     *  prints out message to show that the task is marked as undone
     * @param task to mark as undone
     */
    public static String markedUndone(Task task) {
        return ("Hoot Hoot! I've marked this task as not done yet:\n" + task.toString() + "\n");
    }

    /**
     * prints out message to show that the task is deleted
     * @param task to be deleted
     * @param size number of tasks remaining after deletion
     */
    public static String taskDeleted(Task task, int size) {
        String s = String.format("Hoot Hoot! I've removed this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.\n",task, size);
        return s;
    }

    public static String associationMessage(Task task, String association) {
        return ("Hoot Hoot! I've tagged this task:\n " + task + " \nwith association: " + association);
    }

    public static String findTask(TaskList matchingTask) {
        if (matchingTask.isEmpty()) {
            return ("Hoot Hoot! There are no matching tasks");
        } else {
            String s = ("Hoot Hoot! Here are the matching tasks in your list:\n" + matchingTask + "\n");
            return s;
        }
    }

    public static String filterTask(TaskList filteredTask) {
        if (filteredTask.isEmpty()) {
            return ("Hoot Hoot! There are no matching tasks");
        } else {
            String s = ("Hoot Hoot! Here are the filtered tasks in your list:\n" + filteredTask + "\n");
            return s;
        }
    }

}
