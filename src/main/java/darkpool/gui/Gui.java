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
     * Displays the goodbye message.
     */
    public String goodbye() {
        return bye;
    }

    /**
     * Displays the list of tasks.
     *
     * @param taskList the list of tasks to be displayed
     */
    public String list(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Displays a message indicating a task has been marked.
     *
     * @param task the task that has been marked
     */
    public String mark(String task) {
        return "why do i have to mark this mess\n" + task;
    }

    /**
     * Displays a message indicating a task has been unmarked.
     *
     * @param task the task that has been unmarked
     */
    public String unmark(String task) {
        return "why do i have to unmark this mess\n" + task;
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task the task that has been deleted
     */
    public String delete(String task) {
        return "why do i have to delete this mess\n" + task;
    }

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task the task that has been added
     * @param size the new size of the task list
     */
    public String add(String task, int size) {
        return "i have dumped this nonsense on the list\n" + task
                + "\nnow you are stuck with " + size + " goddamn tasks";
    }

}
