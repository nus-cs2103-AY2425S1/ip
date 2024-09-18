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
        return ("Hello! I'm Bellroy\n" +
                "What can I do for you?\n");
    }

    /**
     * prints the farewell message when user closes the Chatbot
     */
    public String byeMessage() {
        return ("Bye. Hope to see you again soon!\n");
    }

    /**
     * prints a message to show that the task has been added
     * @param task to be added to the list
     * @param size number of tasks after adding this task
     */
    public String taskAddedMessage(Task task, int size) {
        String s = String.format("Got it. I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.\n",task, size);
        return s;
    }

    /**
     * Displays all tasks to the user in the form of a list
     * @param taskList all tasks in List form
     */
    public String printTaskList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * prints out message to show that the task is marked as done
     * @param task to mark as done
     */
    public String markedDone(Task task) {
        return ("Nice! I've marked this task as done:\n" + task.toString() + "\n");
    }

    /**
     *  prints out message to show that the task is marked as undone
     * @param task to mark as undone
     */
    public String markedUndone(Task task) {
        return ("OK, I've marked this task as not done yet:\n" + task.toString() + "\n");
    }

    /**
     * prints out message to show that the task is deleted
     * @param task to be deleted
     * @param size number of tasks remaining after deletion
     */
    public String taskDeleted(Task task, int size) {
        String s = String.format("Got it. I've removed this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.\n",task, size);
        return s;
    }

    public String associationMessage(Task task, String association) {
        return ("Got it. I've tagged this task:\n " + task + " \nwith association: " + association);
    }

    public String findTask(TaskList matchingTask) {
        if (matchingTask.isEmpty()) {
            return ("There are no matching tasks");
        } else {
            String s = ("Here are the matching tasks in your list:\n" + matchingTask + "\n");
            return s;
        }
    }

    public String filterTask(TaskList filteredTask) {
        if (filteredTask.isEmpty()) {
            return ("There are no matching tasks");
        } else {
            String s = ("Here are the filtered tasks in your list:\n" + filteredTask + "\n");
            return s;
        }
    }

}
