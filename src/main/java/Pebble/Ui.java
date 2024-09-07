package pebble;

import java.util.ArrayList;

/**
 * User interface that currently handles output to screen
 */
public class Ui {

    /**
     * Print welcome message
     */
    public void showWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Pebble");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     *  Print exit message
     */
    public void showGoodbye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    /**
     *  Macro to print 1 line
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Print out all the tasks
     * @param tasksList List of tasks to be printed
     */
    public void showTasksList(ArrayList<Task> tasksList) {
        showLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasksList.get(i).toString());
        }
        showLine();
    }

    /**
     * Print out UI message when task is added
     * @param task The task that is added
     * @param size Size of the current task list
     */
    public void showAddTask(Task task, int size) {
        showLine();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task.toString());
        System.out.println("    Now you have " + size + " tasks in this list.");
        showLine();
    }

    /**
     * Print out UI message when a task is deleted
     * @param task The task that is deleted
     * @param size Size of the current task list
     */
    public void showDeleteTask(Task task, int size) {
        showLine();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task.toString());
        System.out.println("    Now you have " + size + " tasks in this list.");
        showLine();
    }

    /**
     * Prints out exception
     * @param message Exception message
     */
    public void showError(String message) {
        showLine();
        System.out.println("    " + message);
        showLine();
    }

    /**
     * Prints out UI message when task is marked as done
     * @param task Task that is done
     */
    public void showMarkTask(Task task) {
        showLine();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task.toString());
        showLine();
    }

    /**
     * Prints out UI message when task is marked as undone
     * @param task Task that is undone
     */
    public void showUnmarkTask(Task task) {
        showLine();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task.toString());
        showLine();
    }

    /**
     * Prints UI message when task number is invalid
     */
    public void showInvalidTaskNumber() {
        showLine();
        System.out.println("    Invalid task number.");
        showLine();
    }

    /**
     * General print line statement
     * @param message Message to print to output
     */
    public void showMessage(String message) {
        showLine();
        System.out.println("    " + message);
        showLine();
    }
}
