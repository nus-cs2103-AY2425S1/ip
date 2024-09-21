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
     * Returns string response when task added.
     *
     * @param task       Task added.
     * @param totalTasks Total number of tasks.
     */
    public String showTaskAdded(Task task, int totalTasks) {
        return ("added: " + task + "\n" + totalTasks + " task(s) in the list");
    }

    /**
     * Return string response when task deleted.
     *
     * @param task       Task deleted.
     * @param totalTasks Total number of tasks.
     */
    public String showTaskDeleted(Task task, int totalTasks) {
        return ("deleted: " + task + "\n" + totalTasks + " task(s) left in the list");
    }

    /**
     * Returns string representation of task list.
     *
     * @param tasks ArrayList of task.
     */
    public String showTaskList(ArrayList<Task> tasks) {
        String toReturn = "Your tasks are: \n";
        if (!tasks.isEmpty()) {
            for (int i = 0; i < tasks.size(); i++) {
                toReturn = toReturn + (i + 1) + "." + tasks.get(i) + "\n";
            }
            return toReturn;
        }
        return "no tasks present";
    }

    /**
     * Returns string response after marking task.
     *
     * @param task Task getting marked.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I have marked this task as done:\n" + task;
    }

    /**
     * Returns string response after unmarking task.
     *
     * @param task Task getting unmarked.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I have marked this task as not done yet\n" + task;
    }


    /**
     * Returns string response after finding matched tasks.
     *
     * @param tasks Array of tasks that matched with keyword.
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
