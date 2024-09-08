package echo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a list of Task objects.
 * It provides methods to operate on a target Task object in the list.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> allTasks = new ArrayList<>();

    /**
     * Returns all tasks in the task list of EchoBot.
     *
     * @return A string containing all tasks in the list.
     */
    public String listAllTask() {
        if (this.allTasks.isEmpty()) {
            return "Emmm, there is no task in the list." + "\n"
                    + "Please feel free to add some tasks!";
        }

        String tasks = "";
        // Get task from task list
        for (int i = 0; i < this.allTasks.size(); i++) {
            tasks += (i + 1) + ". " + this.allTasks.get(i).toString();
            tasks += (i == this.allTasks.size() - 1) ? "" : "\n";
        }

        return "There are " + this.allTasks.size() + " tasks in the list:"
                + "\n" + tasks;
    }

    /**
     * Marks the target task in the list based on the index.
     *
     * @param index index of target Task object.
     * @return A string confirming that the task has been marked as done.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    public String markTask(int index) throws IndexOutOfBoundsException {
        Task t = this.allTasks.get(index);
        t.setMark();
        return "Nice! I've marked this task as done:" + "\n" + t.toString();
    }

    /**
     * Unmarks the target task in the list based on the index.
     *
     * @param index index of target Task object.
     * @return A string confirming that the task has been marked as not done.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    public String unmarkTask(int index) throws IndexOutOfBoundsException {
        Task t = this.allTasks.get(index);
        t.setUnmark();
        return "OK, I've marked this task as not done yet:" + "\n" + t.toString();
    }

    /**
     * Adds a Task to the task list.
     *
     * @param task Task object to add to the list.
     * @return A string confirming the task was added, along with the total number of tasks in the list.
     */
    public String add(Task task) {
        this.allTasks.add(task);
        return task + "\n" + "Now you have "
                + this.allTasks.size() + " tasks in the list.";
    }

    /**
     * Deletes the target Task object in the list.
     *
     * @param index index of target Task object.
     * @return A string confirming that the task was deleted, along with the number of remaining tasks in the list.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    public String delete(int index) throws IndexOutOfBoundsException {
        String message = "Noted. I've removed this task:" + "\n";
        Task t = allTasks.get(index);
        // Remove the task from list and return the size of list
        allTasks.remove(index);
        return message + t.toString() + "\n" + "Now you have "
                + allTasks.size() + " tasks in the list.";
    }

    /**
     * Prints a list of tasks with target keywords。
     *
     * @param keywords the keywords to search for within the task descriptions.
     * @return A string containing the list of tasks that match the keywords.
     */
    public String find(String keywords) {
        CharSequence cs = keywords;
        ArrayList<Task> result = new ArrayList<>();
        // Traversal the list to find Task object with keywords in description
        for (Task t : this.allTasks) {
            if (t.toString().contains(cs)) {
                result.add(t);
            }
        }

        // print search result
        String tasks = "";
        for (int i = 0; i < result.size(); i++) {
            tasks += (i + 1) + ". " + result.get(i).toString();
            tasks += (i == result.size() - 1) ? "" : "\n";
        }

        return "Here are the matching tasks in your list:" + "\n" + tasks;
    }

    @Override
    public String toString() {
        return this.allTasks.toString();
    }
}
