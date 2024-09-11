package UI;

import Tasks.Task;

/**
 * Provides user interface functionalities for displaying messages related to task operations.
 */
public class Ui {

    /**
     * Prints a welcome message to the user.
     * This message introduces the application and gives a friendly greeting.
     */
    public static String welcomeMessage() {
        String res = "";
        res += "Hello! I'm UI.Delphi, the greatest oracle in all of the classical world.\n";
        res += "For now, I will be acting as a personal assistant for you to manage; your tasks:)";
        //System.out.println("Hello! I'm UI.Delphi, the greatest oracle in all of the classical world.");
        //System.out.println("For now, I will be acting as a personal assistant for you to manage your tasks:)");
        return res;
    }

    /**
     * Prints a goodbye message to the user.
     * This message is displayed when the user exits the application.
     */
    public String goodbyeMessage() {
        //System.out.println("Bye. Hope to see you again soon!");
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a message indicating that a task has been added to the list.
     *
     * @param tsk The task that was added.
     * @param num The current number of tasks in the list.
     */
    public String taskMessage(Task tsk, int num) {
        String res = "";
        res += "    Got it. I've added this task:\n";
        res += "      " + tsk + "\n";
        res += "Now you have " + num + " tasks in the list.";
        //System.out.println("    Got it. I've added this task:");
        //System.out.println("      " + tsk);
        //System.out.println("Now you have " + num + " tasks in the list.");
        return res;
    }

    /**
     * Prints a message indicating whether a task has been marked as completed or not.
     *
     * @param completed True if the task is completed; false otherwise.
     * @param tsk The task that was marked.
     */
    public static String markingTask(boolean completed, Task tsk) {
        String res = "";
        if (completed) {
            res += "    Nice! I've marked this task as done:\n";
            res += "      " + tsk;
            //System.out.println("    Nice! I've marked this task as done:");
            //System.out.println("      " + tsk);
        } else {
            res += "    OK, I've marked this task as not done yet:\n";
            res += "      " + tsk;
            //System.out.println("    OK, I've marked this task as not done yet:");
            //System.out.println("      " + tsk);
        }
        return res;
    }

    /**
     * Prints a message indicating that a task has been removed from the list.
     *
     * @param removedTask The task that was removed.
     * @param num The current number of tasks in the list after removal.
     */
    public String removingTask(Task removedTask, int num) {
        String res = "";
        res += "    Noted. I've removed this task:\n";
        res += "      " + removedTask;
        res += "    Now you have " + num + " tasks in the list";
        //System.out.println("    Noted. I've removed this task:");
        //System.out.println("      " + removedTask);
        //System.out.println("    Now you have " + num + " tasks in the list");
        return res;
    }

    /**
     * Prints a message indicating that matching tasks are being displayed.
     * This is typically shown when searching for tasks in the list.
     */
    public String findingTask() {
        return "    Here are the matching tasks in your list:\n";
        //.out.println("    Here are the matching tasks in your list:");
    }
}

