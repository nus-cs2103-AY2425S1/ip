package david.ui;

import java.util.Scanner;

import david.task.Task;
import david.task.TaskList;

/**
 * Ui class for I/O operations
 */
public class Ui {
    private static final String INTRO = " Hello! I'm David.\n"
            + " What can I do for you?\n";

    private static final String OUTRO = "Bye. Hope to see you again soon!\n";


    private Scanner sc;


    /**
     * Constructor for UI interface
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the introduction message
     */
    public String start() {
        return INTRO;
    }

    /**
     * Prints the exit message
     */
    public String end() {
        return OUTRO;
    }

    /**
     * Waits for user's next input
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Displays a successful sorting message
     * @param tasks TaskList of tasks
     * @return Formatted string response
     */
    public String displaySuccessfulSortMessage(TaskList tasks) {
        return "Got it. I have sorted your tasks as specified.\n"
                + "This is your new list of tasks\n"
                + tasks.toString();
    }


    /**
     * Displays the task details
     * @param t Task to display
     * @param noOfTasks Current size of arraylist
     */
    public String displayTaskDetails(Task t, int noOfTasks) {
        return "Got it. I've added this task:\n"
                + t
                + "\n"
                + "     You now have "
                + noOfTasks
                + " tasks in the list.\n";
    }

    /**
     * Displays successful delete message
     * @param t Task to delete
     * @param noOfTasks Current size of arraylist
     */
    public String displaySuccessfulDeleteMessage(Task t, int noOfTasks) {
        return "Alright, I've removed this task from the list:\n"
                + t
                + "\n"
                + "     You now have "
                + noOfTasks
                + " tasks in the list.\n";
    }

    /**
     * Displays successful marking of a task
     * @param t Task to mark as done
     */
    public String displayMarkAsDoneMessage(Task t) {
        return "Nice! I've marked this task as done:\n"
                + t
                + "\n";
    }

    /**
     * Displays successful unmarking of a task
     * @param t Task to unmark as done
     */
    public String displayMarkAsUnDoneMessage(Task t) {
        return "Okay, I've marked this task as not done yet:\n"
                + t
                + "\n";
    }

    /**
     * Displays exception message
     * @param e exception to handle
     */
    public String displayErrorMessage(Exception e) {
        return e.toString();
    }

    /**
     * Displays exception message
     * @param s custom string message to display
     */
    public String displayErrorMessage(String s) {
        return s;
    }

    /**
     * Displays the arraylist of tasks
     * @param tasks TaskList of tasks
     */
    public String listTasks(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Calls the findEvent() method of TaskList that returns the String format of all events matching
     * the specified string
     * @param s specified event String
     * @param tasks TaskList of all tasks
     */
    public String findEvent(String s, TaskList tasks) {
        return tasks.findEvent(s);
    }
}
