package Cookie;

import java.util.ArrayList;

public class Ui {

    /**
     * Prints robot art.
     */
    public void printLogo() {
        String logo = "    o      o    \n"
                + " ____\\____/____\n"
                + "|   _      _   |\n"
                + "|  / \\    / \\  |   /\n"
                + "|  \\_/    \\_/  |  /\n"
                + "|              | /\n"
                + "|______________|/\n"
                + "\n";
        System.out.println(logo);
    }

    /**
     * Prints greeting message.
     */
    public void printGreet() {
        String greet = "Hello! I'm Cookie\n"
                + "How can I help you?\n"
                + "Here are some commands you can use:\n"
                + "todo, deadline, event, mark, unmark, delete, list";
        System.out.println(greet);
    }

    /**
     * Prints exit message.
     */
    public void printQuit() {
        String bye = "Bye. See you soon!";
        System.out.println(bye);
    }

    /**
     * Prints the number of task.
     */
    public void printNoTasksInList(ArrayList<Task> taskArrayList) {
        if (taskArrayList.size() == 1) {
            System.out.println("Now you have " + taskArrayList.size() + " task in the list.\n");
        } else {
            System.out.println("Now you have " + taskArrayList.size() + " tasks in the list.\n");
        }
    }

    /**
     * Prints the latest task to be added to the list.
     */
    public void printLatestTask(Task task) {
        System.out.println("Got it. Cookie has added this task:\n  "
                + task.toString());
    }

    /**
     * Prints a message when a task has been deleted from the list.
     */
    public void printDeleteTask(Task task) {
        String delete = "Cookie has removed the following task from your list:\n" +
                task.toString();
        System.out.println(delete);
    }

    /**
     * Prints a message when a task is successfully marked as done.
     */
    public void printMarkTask(Task task) {
        String mark = "Cookie has marked this as done! Good job! \n" +
                task.toString();
        System.out.println(mark);
    }

    /**
     * Prints a message when a task is successfully marked as not done.
     */
    public void printUnmarkTask(Task task) {
        String unmark = "Cookie has unmarked this task! \n" +
                task.toString();
        System.out.println(unmark);
    }

}
