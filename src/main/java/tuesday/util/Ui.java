package tuesday.util;

import java.util.Scanner;
import java.lang.StringBuilder;

import tuesday.task.Task;

/**
 * Handles all the String outputs to be displayed and interact with the user.
 */
public class Ui {
    public Ui() {

    }

    /**
     *  Returns an error message when there is no data file
     */
    public String showLoadingError() {
        return this.showLine()
                + "    Hey there! It does not seem like you have a datafile. I will make one for you :D\n"
                + this.showLine();
    }

    /**
     *  Returns a welcome message
     */
    public String showWelcome() {
        return this.showLine()
                + "    Hello! I'm Tuesday, a randomly created bot."
                + "\n    You may type 'help' to show what functions are available\n"
                + this.showLine();
    }

    /**
     *  Returns a goodbye message
     */
    public String showBye() {
        return this.showLine()
                + "    Bye bye. Hope to see you again soon!\n"
                + this.showLine();
    }

    /**
     *  Reads the lines typed out & returns the command
     */
    public String readCommand() {
        Scanner scannerObj = new Scanner(System.in); // Create a Scanner object
        return scannerObj.nextLine();
    }

    /**
     *  Returns the current task added
     */
    public String showTaskCount() {
        return this.showLine()
                + "    Got it. I've added this task:\n     "
                + Task.getTaskArrayList().get(Task.getCount() - 1).toString()
                + "\n    Now you have " + Task.getCount() + " task(s) in the list.\n"
                + this.showLine();
    }

    /**
     *  Returns the current list of tasks
     */
    public String showList() {
        StringBuilder msg = new StringBuilder();

        if (Task.getCount() == 0) {
            msg.append("    You have no task in your list\n");
        } else {
            msg.append("    Here are the task(s) in your list:\n");
            for (int n = 0; n < Task.getCount(); n++) {
                msg.append("\n    " + (n + 1) + "." + Task.getTaskArrayList().get(n).toString());
            }
        }

        return this.showLine()
                + msg
                + "\n"
                + this.showLine();
    }

    /**
     *  Prints the current list of tasks
     */
    public String showMarkMessage(int index, boolean isMarked) {
        if (isMarked) {
            return this.showLine()
                    + "    Nice! I've marked this task as done: \n    "
                    + Task.getTaskArrayList().get(index).toString()
                    + "\n"
                    + this.showLine();
        } else {
            return this.showLine()
                    + "    OK, I've marked this task as not done yet: \n    "
                    + Task.getTaskArrayList().get(index).toString()
                    + "\n"
                    + this.showLine();
        }
    }

    /**
     * Prints the delete function message
     *
     * @param index Index of the object that will be deleted
     */
    public String showDeleteMessage(int index) {
        return this.showLine()
                + "    Got it. I've deleted this task:\n    "
                + Task.getTaskArrayList().get(index).toString()
                + "\n    Now you have " + (Task.getCount() - 1) + " task(s) in the list.\n"
                + this.showLine();
    }

    /**
     * Prints a list of tasks that matches the key
     *
     * @param keyMessageToFind Key to find
     */
    public String showFindMessage(String keyMessageToFind) {
        StringBuilder msg = new StringBuilder();
        int n = 1;

        for (Task tasks : Task.getTaskArrayList()) {
            n = checkHelper(msg, tasks, n, keyMessageToFind);
        }
        if (n == 1) {
            msg.append("    There are no matching tasks in your list\n");
        }
        System.out.println(this.showLine()
                + msg
                + this.showLine());
        return this.showLine()
                + msg
                + this.showLine();
    }

    private int checkHelper(StringBuilder msg, Task tasks, int n, String keyMessageToFind) {
        if (tasks.getDescription().contains(keyMessageToFind)) {
            if (n == 1) {
                msg.append("    Here are the matching tasks in your list\n");
            }
            msg.append("    " + (n) + "." + tasks.toString() + "\n");
            n++;
        }
        return n;
    }

    /**
     *  returns a divider line
     */
    private String showLine() {
        //System.out.println("    _______________________________");
        return "    _______________________________\n";
    }

    /**
     * Prints the error message
     *
     * @param errorMsg Error message
     */
    public String showError(String errorMsg) {
        return "Error: There is a problem " + errorMsg;
    }
}
