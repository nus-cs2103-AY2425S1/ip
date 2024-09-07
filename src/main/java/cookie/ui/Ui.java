package cookie.ui;

import java.util.ArrayList;

import cookie.task.Task;

public class Ui {

    /**
     * Returns robot art.
     */
    public String printLogo() {
        return "    o      o    \n"
                + " ____\\____/____\n"
                + "|   _      _   |\n"
                + "|  / \\    / \\  |   /\n"
                + "|  \\_/    \\_/  |  /\n"
                + "|              | /\n"
                + "|______________|/\n"
                + "\n";
    }

    /**
     * Returns greeting message.
     */
    public String printGreet() {
        return "Hello! I'm Cookie\n"
                + "How can I help you?\n"
                + "Here are some commands you can use:\n"
                + "todo, deadline, event, mark, unmark, delete, list and find";
    }

    /**
     * Returns exit message.
     */
    public String printQuit() {
        return "Bye. See you soon!";
    }

    /**
     * Returns the number of task.
     */
    public String printNoTasksInList(ArrayList<Task> taskArrayList) {
        if (taskArrayList.size() == 1) {
            return "Now you have " + taskArrayList.size() + " task in the list.\n";
        } else {
            return "Now you have " + taskArrayList.size() + " tasks in the list.\n";
        }
    }

    /**
     * Returns the latest task to be added to the list.
     */
    public String printLatestTask(Task task) {
        return "Got it. Cookie has added this task:\n" + task.toString() + "\n";
    }

    /**
     * Returns a message when a task has been deleted from the list.
     */
    public String printDeleteTask(Task task) {
        return "Cookie has removed the following task from your list:\n" + task.toString();
    }

    /**
     * Returns a message when a task is successfully marked as done.
     */
    public String printMarkTask(Task task) {
        return "Cookie has marked this as done! Good job!\n" + task.toString();
    }

    /**
     * Returns a message when a task is successfully marked as not done.
     */
    public String printUnmarkTask(Task task) {
        return "Cookie has unmarked this task!\n" + task.toString();
    }

    /**
     * Returns all the task in the ArrayList.
     */
    public String printTasks(ArrayList<Task> taskArrayList) {
        int count = 1;
        StringBuilder list = new StringBuilder();
        for (Task task: taskArrayList) {
            list.append(count++).append(": ").append(task.toString()).append("\n");
        }
        return list.toString();
    }
}
