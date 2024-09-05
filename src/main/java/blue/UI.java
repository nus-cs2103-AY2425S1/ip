package blue;

import java.util.ArrayList;

import blue.task.Task;



/**
 * The {@code UI} class handles interactions with the user by displaying messages and task information.
 * It provides methods to greet the user, say farewell, and display task-related updates.
 */
public class UI {

    /**
     * Displays a greeting message to the user.
     */
    public static String greet() {
        return "Hello! I'm Blue! Woof Woof! Yap Yap!\nWhat can I do for you?";
    }


    /**
     * Displays a farewell message to the user.
     */
    public static void farewell() {
        System.out.println("Bye Bye! Hope to see you again soon!");
        System.out.println("_     /)---(\\          /~~~\\");
        System.out.println("\\\\   (/ . . \\)        /  .. \\");
        System.out.println(" \\\\__)-\\(*)/         (_,\\  |_)");
        System.out.println(" \\_       (_         /   \\@/    /^^^\\");
        System.out.println(" (___/-(____) _     /      \\   / . . \\");
        System.out.println("              \\\\   /  `    |   V\\ Y /V");
        System.out.println("               \\\\/  \\   | _\\    / - \\");
        System.out.println("                \\   /__'|| \\\\_  |    \\");
        System.out.println("                 \\_____)|_).\\_).||(__V");
        System.out.println("--------------------------------------------");
    }

    /**
     * Displays a message after a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     */

    public static String displayAfterMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }


    /**
     * Displays a message after a task has been unmarked (marked as not done).
     *
     * @param task The task that has been unmarked.
     */

    public static String displayAfterUnMark(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }


    /**
     * Displays a message after a task has been deleted, along with the updated number of tasks.
     *
     * @param task The task that has been deleted.
     * @param noOfTask The number of remaining tasks in the list.
     */

    public static String displayAfterDelete(Task task, int noOfTask) {
        StringBuilder result = new StringBuilder();
        result.append("Noted. I've removed this task:\n");
        result.append(task.toString()).append("\n");
        result.append("Now you have ").append(noOfTask).append(" tasks in the list.");
        return result.toString();
    }


    /**
     * Displays the current list of tasks.
     *
     * @param myList The list of tasks to display.
     * @param noOfTask The number of tasks in the list.
     */

    public static String displayList(ArrayList<Task> myList, int noOfTask) {
        StringBuilder result = new StringBuilder();
        result.append("Here are the tasks in your list:\n");
        for (int i = 0; i < noOfTask; i++) {
            result.append(i + 1).append(". ").append(myList.get(i).toString()).append("\n");
        }
        return result.toString();
    }

}
