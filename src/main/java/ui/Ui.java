package ui;

import java.util.ArrayList;

import task.Task;

/**
 * The Ui class deals with printing outputs for users
 */
public class Ui {
    private final int lineLength = 50;
    private final String horizontalLine = "\t" + "-".repeat(this.lineLength);

    public void printHorizontalLine() {
        System.out.println(this.horizontalLine);
    }

    /**
     * Greets user upon instantiation of bot
     * @param name String representing the name of the bot
     */
    public void greet(String name) {
        this.printHorizontalLine();
        System.out.println("\t" + "Hello, I'm " + name);
        System.out.println("\t" + "What can I do for you?");
        this.printHorizontalLine();
    }

    /**
     * Prints an exit message upon termination of bot
     */
    public void exit() {
        this.printHorizontalLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        this.printHorizontalLine();
    }

    /**
     * Informs user that task has been added
     * @param t Task that has been added
     * @param size Current number of tasks
     */
    public void printAddTask(Task t, int size) {
        this.printHorizontalLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t);
        System.out.println("\tNow you have " + size + " tasks in the list.");
        this.printHorizontalLine();
    }

    /**
     * Informs user that task has been deleted
     * @param t Task that has been deleted
     * @param size Current number of tasks
     */
    public void printDeleteTask(Task t, int size) {
        this.printHorizontalLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + t);
        System.out.println("\t Now your have " + size + " tasks in the list.");
        this.printHorizontalLine();
    }

    /**
     * Lists out all tasks in the given array
     * @param tasks ArrayList<Task> of tasks to be printed
     */
    public void printTasks(ArrayList<Task> tasks) {
        this.printHorizontalLine();
        if (tasks.size() == 0) {
            System.out.println("\tNo tasks yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
            }
        }
        this.printHorizontalLine();
    }

    /**
     * Informs user that task has been marked as done
     * @param t Task that has been marked as done
     */
    public void printSuccessfulMark(Task t) {
        this.printHorizontalLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + t.toString());
        this.printHorizontalLine();
    }

    /**
     * Informs user that task has been marked as undone
     * @param t Task that has been marked as undone
     */
    public void printSuccessfulUnmark(Task t) {
        this.printHorizontalLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t" + t.toString());
        this.printHorizontalLine();
    }

    /**
     * Informs user of an exception in the program
     * @param e Exception in the program
     */
    public void printException(Exception e) {
        this.printHorizontalLine();
        System.out.println("\t" + e);
        this.printHorizontalLine();
    }
}