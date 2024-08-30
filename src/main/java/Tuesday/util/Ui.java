package Tuesday.util;

import Tuesday.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public Ui() {

    }

    /**
     *  Prints an error message when there is no data file
     */
    public void showLoadingError() {
        this.showLine();
        System.out.println("    Hey there! It does not seem like you have a datafile. I will make one for you :D");
        this.showLine();
    }

    /**
     *  Prints a welcome message
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("    Hello! I'm Tuesday, a randomly created bot.\n    What can I do for you?");
        this.showLine();
    }

    /**
     *  Prints a goodbye message
     */
    public void showBye() {
        this.showLine();
        System.out.println("    Bye bye. Hope to see you again soon!");
        this.showLine();
    }

    /**
     *  Reads the lines typed out & returns the command
     */
    public String readCommand() {
        Scanner scannerObj = new Scanner(System.in); // Create a Scanner object
        return scannerObj.nextLine();
    }

    /**
     *  Reads the lines typed out & returns the command
     */
    public void showTaskCount() {
        this.showLine();
        System.out.println("    Got it. I've added this task:\n     "
                + Task.taskArrayList.get(Task.count - 1).toString()
                + "\n    Now you have " + Task.count + " task(s) in the list.");
        this.showLine();
    }

    /**
     *  Prints the current list of tasks
     */
    public void showList() {
        this.showLine();
        if (Task.count == 0) {
            System.out.println("    You have no task in your list");
        } else {
            System.out.println("    Here are the task(s) in your list:");
            for (int n = 0; n < Task.count; n++) {
                System.out.println("    " + (n + 1) + "." + Task.taskArrayList.get(n).toString());
            }
        }
        this.showLine();
    }

    /**
     *  Prints the current list of tasks
     */
    public void showMarkMessage(int index, boolean isMarked) {
        this.showLine();
        if (isMarked) {
            System.out.println("    Nice! I've marked this task as done: \n    "
                    + Task.taskArrayList.get(index).toString());
        } else {
            System.out.println("    OK, I've marked this task as not done yet: \n    "
                    + Task.taskArrayList.get(index).toString());
        }
        this.showLine();
    }

    /**
     * Prints the delete function message
     *
     * @param index Index of the object that will be deleted
     */
    public void showDeleteMessage(int index) {
        this.showLine();
        System.out.println("    Got it. I've deleted this task:\n      "
                + Task.taskArrayList.get(index).toString()
                + "\n    Now you have " + (Task.count - 1) + " task(s) in the list.");
        this.showLine();
    }

    /**
     * Prints a list of tasks that matches the key
     *
     * @param keyMessageToFind Key to find
     */
    public void showFindMessage(String keyMessageToFind) {
        this.showLine();
        int n = 1;
        for (Task tasks : Task.taskArrayList) {
            if(tasks.getDescription().contains(keyMessageToFind)){
                if (n == 1) {
                    System.out.println("    Here are the matching tasks in your list");
                }
                System.out.println("    " + (n) + "." + tasks.toString());
                n++;
            }
        }
        if (n == 1) {
            System.out.println("    There are no matching tasks in your list");
        }
        this.showLine();
    }

    /**
     *  prints a divider line
     */
    public void showLine() {
        System.out.println("    _______________________________");
    }

    /**
     * Prints the error message
     *
     * @param error_msg Error message
     */
    public void showError(String error_msg) {

    }
}
