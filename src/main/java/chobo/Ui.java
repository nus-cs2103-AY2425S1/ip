package chobo;

import java.util.ArrayList;

/**
 * The type Ui.
 */
public class Ui {
    private static final String LINE = "----------------------------------------";

    /**
     * Print welcome.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm Chobo\nWhat can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Print goodbye.
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Print line.
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Print error.
     *
     * @param message the message
     */
    public String printError(String message) {
        return (message);
    }

    /**
     * Print task added.
     *
     * @param task       the task
     * @param totalTasks the total tasks
     */
    public String printTaskAdded(Task task, int totalTasks) {
        return ("added: " + task + " " + totalTasks + " task(s) in the list");
    }

    /**
     * Print task deleted.
     *
     * @param task       the task
     * @param totalTasks the total tasks
     */
    public String printTaskDeleted(Task task, int totalTasks) {
        return ("deleted: " + task + "\n" + totalTasks + " task(s) left in the list");
    }

    /**
     * Print task list.
     *
     * @param tasks the tasks
     */
    public String printTaskList(ArrayList<Task> tasks) {
        String toReturn = "Your tasks are: \n";
        if (tasks.size() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                toReturn = toReturn + (i + 1) + "." + tasks.get(i) + "\n";
            }
            return toReturn;
        }
        return "no tasks present";
    }

    /**
     * Show task marked.
     *
     * @param task the task
     */
    public String printTaskMarked(Task task) {
        return "Nice! I have marked this task as done:\n" + task;
    }

    /**
     * Show task unmarked.
     *
     * @param task the task
     */
    public String printTaskUnmarked(Task task) {
        return "OK, I have marked this task as not done yet\n" + task;
    }


    /**
     * Print matched tasks.
     *
     * @param tasks the array of tasks
     */
    public String printMatchedTasks(ArrayList<Task> tasks) {
        String toReturn = "The matching tasks in your list: \n";
        if (tasks.isEmpty()) {
            return ("No such tasks.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                toReturn = toReturn + (i + 1) + "." + tasks.get(i) + "\n";
            }
            return toReturn;
        }
    }
}
