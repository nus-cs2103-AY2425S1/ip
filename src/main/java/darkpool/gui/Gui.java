package darkpool.gui;

import darkpool.util.TaskList;

import java.util.Scanner;

/**
 * The Gui class handles user interactions.
 * It provides methods to read commands, display greetings and goodbyes,
 * list tasks, mark and unmark tasks, delete tasks, add tasks, and show errors.
 */
public class Gui {

    final String bye = "contact me again and you'll regret it.";

    /**
     * Reads a command from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Returns the goodbye message.
     *
     * @return the goodbye message
     */
    public String goodbye() {
        return bye;
    }

    /**
     * Returns the list of tasks.
     *
     * @param taskList the list of tasks
     * @return the string representation of the task list
     */
    public String list(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Returns the message for marking a task.
     *
     * @param task the task to be marked
     * @return the message for marking the task
     */
    public String mark(String task) {
        return "why do i have to mark this mess\n" + task;
    }

    /**
     * Returns the message for unmarking a task.
     *
     * @param task the task to be unmarked
     * @return the message for unmarking the task
     */
    public String unmark(String task) {
        return "why do i have to unmark this mess\n" + task;
    }

    /**
     * Returns the message for deleting a task.
     *
     * @param task the task to be deleted
     * @return the message for deleting the task
     */
    public String delete(String task) {
        return "why do i have to delete this mess\n" + task;
    }

    /**
     * Returns the message for adding a task.
     *
     * @param task the task to be added
     * @param size the current size of the task list
     * @return the message for adding the task
     */
    public String add(String task, int size) {
        return "i have dumped this nonsense on the list\n" + task
                + "\nnow you are stuck with " + size + " goddamn tasks";
    }

}