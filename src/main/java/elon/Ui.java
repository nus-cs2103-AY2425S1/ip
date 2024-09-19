package elon;

import elon.task.Deadline;
import elon.task.Event;
import elon.task.Task;
import elon.task.TaskList;
import elon.task.ToDo;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Provides user interface functions for interacting with the task list and its tasks.
 */
public class Ui {
    private Scanner scanner;

    public Ui () {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Draws a horizontal line in the console for separation.
     */
    public String drawLine() {
        return "-------------------------------------------------------\n";
    }

    /**
     * Prints a greeting message to the console.
     */
    public static String greetMessage() {
        return "Hello! I'm Elon\n" +  "What can I do for you?\n";
    }

    /**
     * Prints a goodbye message to the console.
     */
    public String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String[] getInputArr() {
        return scanner.nextLine().split(" ");
    }

    public String showInvalidIndex() {
        return "Index out of range.\n";
    }

    /**
     * Prints the tasks in the list to the console.
     * If the list is empty, prints a message indicating that there are no tasks.
     * Otherwise, prints each task with its index, incremented by 1 from the index in the list.
     *
     * @param list the TaskList containing tasks to be displayed
     */
    public String listTasks(TaskList list) {
        if (list.isEmpty()) {
            return "There are no tasks in your list.\n";
        } else {
            String res = "Here are the tasks in your list:\n";
            for (int i = 0; i < list.listSize(); i++) {
                res += String.format("%d.", i + 1) + list.getTask(i).toString() + "\n";
            }
            return res;
        }
    }

    /**
     * Marks the task at the specified index as done and prints the task to the console.
     * If the task is already marked as done, prints a message indicating so.
     *
     * @param index the index of the task to mark as done
     * @param list the TaskList containing the task
     */
    public String markTask(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString() + "\n";
    }

    /**
     * Marks the task at the specified index as not done and prints the task to the console.
     * If the task is already marked as not done, prints a message indicating so.
     *
     * @param index the index of the task to mark as not done
     * @param list  the TaskList containing the task
     */
    public String unmarkTask(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString() + "\n";
    }

    /**
     * Prints a message indicating that a task has been added to the list.
     */
    public String startAddTask() {
        return "Got it. I've added this task:\n";
    }

    /**
     * Prints a message indicating the updated number of tasks in the list after adding a new task.
     *
     * @param size the updated number of tasks in the list
     */
    public String endAddTask(int size) {
        return String.format("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Adds a ToDo task to the task list based on the provided input array.
     * Throws an ElonException if the description is missing.
     *
     * @param inputArr the input array containing the task description
     * @param list the TaskList to add the ToDo task to
     * @throws ElonException if the description is not specified
     */
    public String addTask(Task task, TaskList list) {
        return startAddTask() + task.toString() + "/n" + endAddTask(list.listSize());
    }

    /**
     * Deletes the task at the specified index from the task list and prints a message to console.
     *
     * @param index the index of the task to delete
     * @param list the TaskList containing the task
     */
    public String deleteTask(Task task, TaskList list) {
        return "Noted. I've removed this task:\n" + task.toString() + "\n" + endAddTask(list.listSize());
    }

    /**
     * Displays the tasks that match the given keyword.
     *
     * @param matchingTasks the list of tasks that match the keyword
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.\n";
        } else {
            String res = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                res += String.format("%d.%s\n", i + 1, matchingTasks.get(i).toString());
            }
            return res;
        }
    }
}
