package chobo;

import java.util.ArrayList;

/**
 * The type Ui.
 */
public class Ui {


    public String showWelcome() {
        return "Hello! I'm Chobo\nWhat can I do for you?";      
    }

    /**
     * to Print goodbye.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }
    /**
     * Print error.
     *
     * @param message the message
     */
    public String showError(String message) {
        return (message);
    }

    /**
     * Print task added.
     *
     * @param task       the task
     * @param totalTasks the total tasks
     */
    public String showTaskAdded(Task task, int totalTasks) {
        return ("added: " + task + "\n" + totalTasks + " task(s) in the list");
    }

    /**
     * Print task deleted.
     *
     * @param task       the task
     * @param totalTasks the total tasks
     */
    public String showTaskDeleted(Task task, int totalTasks) {
        return ("deleted: " + task + "\n" + totalTasks + " task(s) left in the list");
    }

    /**
     * Print task list.
     *
     * @param tasks the tasks
     */
    public String showTaskList(ArrayList<Task> tasks) {
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
    public String showTaskMarked(Task task) {
        return "Nice! I have marked this task as done:\n" + task;
    }

    /**
     * Show task unmarked.
     *
     * @param task the task
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I have marked this task as not done yet\n" + task;
    }


    /**
     * Print matched tasks.
     *
     * @param tasks the array of tasks
     */
    public String showMatchedTasks(ArrayList<Task> tasks) {
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
