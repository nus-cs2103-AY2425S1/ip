package david.UI;


import david.Task.Task;
import david.Task.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private final static String intro =
            "____________________________________________________________\n" +
            " Hello! I'm David.\n" +
            " What can I do for you?\n" +
            "____________________________________________________________";
    private final static String outro =
            "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________\n";

    /**
     * Constructor for UI interface
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the introduction message
     */
    public void start() {
        System.out.println(intro);
    }

    /**
     * Prints the exit message
     */
    public void end() {
        System.out.println(outro);
    }

    /**
     * Waits for user's next input
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Displays the task details
     * @param t Task to display
     * @param noOfTasks Current size of arraylist
     */
    public void displayTaskDetails(Task t, int noOfTasks) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        t + "\n" +
                        "     You now have " + noOfTasks +  " tasks in the list.\n" +
                        "____________________________________________________________\n"
        );
    }

    /**
     * Displays successful delete message
     * @param t Task to delete
     * @param noOfTasks Current size of arraylist
     */
    public void displaySuccessfulDeleteMessage(Task t, int noOfTasks) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Alright, I've removed this task from the list:\n" +
                        t + "\n" +
                        "     You now have " + noOfTasks +  " tasks in the list.\n" +
                        "____________________________________________________________\n");
    }

    /**
     * Displays successful marking of a task
     * @param t Task to mark as done
     */
    public void displayMarkAsDoneMessage(Task t) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Nice! I've marked this task as done:\n" +
                        t + "\n" +
                        "____________________________________________________________\n");
    }

    /**
     * Displays successful unmarking of a task
     * @param t Task to unmark as done
     */
    public void displayMarkAsUnDoneMessage(Task t) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Okay, I've marked this task as not done yet:\n" +
                        t + "\n" +
                        "____________________________________________________________\n");
    }

    /**
     * Displays exception message
     * @param e exception to handle
     */
    public void displayErrorMessage(Exception e) {
        System.out.println(e.toString());
    }

    /**
     * Displays exception message
     * @param s custom string message to display
     */
    public void displayErrorMessage(String s) {
        System.out.println(s);
    }

    /**
     * Displays the arraylist of tasks
     * @param tasks TaskList of tasks
     */
    public void listTasks(TaskList tasks) {
        System.out.println(tasks.toString());
    }
}
