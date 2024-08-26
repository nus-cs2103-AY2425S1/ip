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
     */
    public void listAllTask() {
        String tasks = "";
        // Get task from task list
        for (int i = 0; i < this.allTasks.size(); i++) {
            tasks += (i + 1) + ". " + this.allTasks.get(i).toString();
            tasks += (i == this.allTasks.size() - 1) ? "" : "\n";
        }
        System.out.println(tasks);
    }

    /**
     * Marks the target task in the list based on the index.
     *
     * @param index index of target Task object.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        this.allTasks.get(index).setMark();
    }

    /**
     * Unmarks the target task in the list based on the index.
     *
     * @param index index of target Task object.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        this.allTasks.get(index).setUnmark();
    }

    /**
     * Adds a Task to the task list.
     *
     * @param task Task object to add to the list.
     */
    public void add(Task task) {
        this.allTasks.add(task);
        System.out.println(task);
        System.out.println("Now you have " + this.allTasks.size() +" tasks in the list.");
    }

    /**
     * Deletes the target Task object in the list.
     *
     * @param index index of target Task object.
     * @throws IndexOutOfBoundsException if index is out of bound.
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        System.out.println("Noted. I've removed this task:");
        System.out.println(allTasks.get(index));

        // Remove the task from list and return the size of list
        allTasks.remove(index);
        System.out.println("Now you have " + allTasks.size() +" tasks in the list.");
    }

    @Override
    public String toString() {
        return this.allTasks.toString();
    }
}
