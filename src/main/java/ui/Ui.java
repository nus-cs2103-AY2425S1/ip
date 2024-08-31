package ui;

import task.Task;
import task.TaskList;
import prince.Prince;


import java.util.ArrayList;

/**
 * Handles the user interface interactions for displaying and updating task-related messages.
 * Output messages include displaying tasks, and adding or removing tasks.
 * It also includes methods for printing error messages and termination messages.
 */
public class Ui {

    /**
     * Generates a string displaying all tasks in the list.
     * @param list
     * @return String format of tasks
     */
    public static String listDisplay(ArrayList<Task> list) {
        int length = list.size();
        // use String Builder to ensure that the string can be created on another line
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; i++) {
            sb.append(i + 1 + ". " + list.get(i).printTask()).append("\n");
        }

        return "Here are the tasks in your list:\n" + sb.toString();
    }

    /**
     * Generates a string confirming that a task has been added to the list.
     * @param task
     * @return string
     */

    public static String taskAddDescription(Task task) {
        return "Got it. I've added this task:\n" + "  " + task.printTask() + "\n" +
                "Now you have " + TaskList.getList().size() + " tasks in the list";
    }

    /**
     * Generates a string confirming that a task has been removed from the list.
     * @param num
     * @param task
     * @return string
     */

    public static String taskDelDescription(int num, Task task){
        return "Noted. I've removed this task:\n" + "  " + task.printTask() + "\n" +
                "Now you have " + TaskList.getList().size() + " tasks in the list";
    }

    /**
     * Prints a termination message to the console.
     */

    public void terminationMessage() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Prints an error message to the console
     * @param message
     */

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

}
