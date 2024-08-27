package main;

import task.Task;

/**
 *  This class is used to display all the output that the user may see such as
 *  greeting messages or acknowledgement of actions being done
 */
public class Ui {
    public Ui() {

    }

    /**
     * Displays a greeting message that is used when program is first ran
     */
    public void greetingMessage() {
        String greeting = "________________________________\n"
                + "Hello! I'm main.Bean\n"
                + "What can i do for you?\n"
                +"________________________________";
        System.out.println(greeting);
    }

    /**
     * Displays a farewell message when program terminates
     */
    public void byeMessage() {
        String byeMsg =
                "________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "________________________________";
        System.out.println(byeMsg);
    }

    /**
     * Displays acknowledgement of task being added to task list as well as the size of current
     * task list
     * @param task the task that is being added
     * @param size size of current task list
     */
    public void addMessage(Task task, int size) {
        String output = "________________________________\n" + "Got it. I've added this task:";
        System.out.println(output);
        System.out.println(task.getString());
        output = "Now you have " + String.valueOf(size) + " tasks in the list.\n" + "________________________________";
        System.out.println(output);
    }

    /**
     * Displays acknowledgement of task being marked as done
     * @param task the task that is being marked
     */
    public void markedMessage(Task task) {
        System.out.println("________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getString());
        System.out.println("________________________________");
    }

    /**
     * Displays acknowledgement of task being unmarked as undone
     * @param task the task that is being unmarked
     */
    public void unmarkedMessage(Task task) {
        System.out.println("________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.getString());
        System.out.println("________________________________");
    }

    /**
     * Displays acknowledgement of task being deleted from task list as well as the size of current
     * task list
     * @param task the task that is being deleted
     * @param size size of current task list
     */
    public void deletedMessage(Task task, int size) {
        System.out.println("________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getString());
        System.out.println( "Now you have " + String.valueOf(size) + " tasks in the list.\n" + "________________________________");
    }
}
