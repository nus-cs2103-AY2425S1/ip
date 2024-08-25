package david.UI;

import david.Task.*;

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

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void start() {
        System.out.println(intro);
    }

    public void end() {
        System.out.println(outro);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void displayTaskDetails(Task t, int noOfTasks) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        t + "\n" +
                        "     You now have " + noOfTasks +  " tasks in the list.\n" +
                        "____________________________________________________________\n"
        );
    }

    public void displaySuccessfulDeleteMessage(Task t, int noOfTasks) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Alright, I've removed this task from the list:\n" +
                        t + "\n" +
                        "     You now have " + noOfTasks +  " tasks in the list.\n" +
                        "____________________________________________________________\n");
    }

    public void displayMarkAsDoneMessage(Task t) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Nice! I've marked this task as done:\n" +
                        t + "\n" +
                        "____________________________________________________________\n");
    }

    public void displayMarkAsUnDoneMessage(Task t) {
        System.out.println(
                "____________________________________________________________\n" +
                        "Okay, I've marked this task as not done yet:\n" +
                        t + "\n" +
                        "____________________________________________________________\n");
    }

    public void displayErrorMessage(Exception e) {
        System.out.println(e.toString());
    }
    public void displayErrorMessage(String s) {
        System.out.println(s);
    }

    public void listTasks(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    /**
     * Calls the findEvent() method of TaskList that returns the String format of all events matching
     * the specified string
     * @param s specified event String
     * @param tasks TaskList of all tasks
     */
    public void findEvent(String s, TaskList tasks) {
        System.out.println(tasks.findEvent(s));
    }
}
