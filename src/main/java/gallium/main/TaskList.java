package gallium.main;

import gallium.task.Task;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of Task objects. It provides methods to
 * add, remove, retrieve and string represent tasks.
 */
public class TaskList {

    private ArrayList<Task> taskArrayList;

    /**
     * Constructs a TaskList object with a list of Tasks.
     * 
     * @param taskArrayList The ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<Task>();
    }

    /**
     * Returns a string representation of the TaskList.
     * 
     * @return A string representation of the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 1; i <= taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i - 1);
            listStringBuilder.append(task.toString()).append("\n");
        }
        String listString = listStringBuilder.toString();
        return listString;
    }

    /**
     * Returns a string representation of the TaskList with each task indented and
     * numbered.
     * 
     * @return A string representation of the TaskList with indent and
     *         index.
     */
    public String toStringIndent() {
        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 1; i <= taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i - 1);
            listStringBuilder.append("    ").append(i + ". " + task.toString()).append("\n");
        }
        String listString = listStringBuilder.toString();
        return listString;
    }

    /**
     * Retrieves the Task at an index in the TaskList.
     * 
     * @param index The index of the Task to retrieve.
     * @return The Task at the index.
     */
    public Task getTask(int index) {
        return this.taskArrayList.get(index);
    }

    /**
     * Adds a Task to the TaskList.
     * 
     * @param task The Task to add to the TaskList.
     */
    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    /**
     * Removes the Task at an index from the TaskList.
     * 
     * @param index The index of the Task to remove.
     */
    public void remove(int index) {
        this.taskArrayList.remove(index);
    }
}
