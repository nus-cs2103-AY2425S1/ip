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
    public void displayGreetingMessage() {

        String greeting = "________________________________\n"
                + "Hello! I'm main.Bean\n"
                + "What can i do for you?\n"
                + "________________________________";
        System.out.println(greeting);
    }


    /**
     * Displays a farewell message when program terminates
     */
    public String displayByeMessage() {
        String byeMsg =
                "________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "________________________________";
        System.out.println(byeMsg);
        return byeMsg;
    }

    /**
     * Displays acknowledgement of task being added to task list as well as the size of current
     * task list
     * @param task the task that is being added
     * @param size size of current task list
     */
    public String displayAddMessage(Task task, int size) {
        assert task != null : "Task should not be null";
        assert size >= 0 : "Size of task list should not be negative";
        String output = "Got it. I've added this task:\n" + task.getString() + "\n"
                + "Now you have " + size + " tasks in the list.";
        System.out.println(output);
        System.out.println("________________________________");
        return output;
    }


    /**
     * Displays acknowledgement of task being marked as done
     * @param task the task that is being marked
     */
    public String displayMarkedMessage(Task task) {
        assert task != null : "Task should not be null";
        System.out.println("________________________________");
        String output = "Nice! I've marked this task as done:\n" + task.getString();
        System.out.println(output);
        System.out.println("________________________________");
        return output;
    }

    /**
     * Displays acknowledgement of task being unmarked as undone
     * @param task the task that is being unmarked
     */
    public String displayUnmarkedMessage(Task task) {
        assert task != null : "Task should not be null";
        System.out.println("________________________________");
        String output = "OK, I've marked this task as not done yet:\n" + task.getString();
        System.out.println(output);
        System.out.println("________________________________");
        return output;
    }

    /**
     * Displays acknowledgement of task being deleted from task list as well as the size of current
     * task list
     * @param task the task that is being deleted
     * @param size size of current task list
     */

    public String displayDeletedMessage(Task task, int size) {
        assert task != null : "Task should not be null";
        assert size >= 0 : "Size of task list should not be negative";
        System.out.println("________________________________");
        String output = "Noted. I've removed this task:\n" + task.getString() + "\n";
        output += "Now you have " + size
                + " tasks in the list.";
        System.out.println(output);
        System.out.println("________________________________");
        return output;
    }
}
