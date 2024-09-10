package kita;

import java.util.ArrayList;

import kita.exceptions.KitaOutOfBounds;

/**
 * The TaskList class manages a list of Task objects.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the ArrayList of Task objects stored in this TaskList.
     *
     * @return ArrayList of Task objects.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Adds a given Task object to the list.
     *
     * @param taskObj The Task to add.
     */
    public void addTask(Task taskObj) {
        this.tasks.add(taskObj);
    }

    /**
     * Returns a Task object given an index.
     *
     * @param index The index of the Task to return.
     * @return Task The task at the specified index.
     * @throws KitaOutOfBounds If the given index is out of bounds.
     */
    public Task getTask(Integer index) {
        if (index >= this.tasks.size() || index < 0) {
            throw new KitaOutOfBounds();
        }

        return this.tasks.get(index);
    }

    /**
     * Removes a task at the specified index and returns it.
     *
     * @param index The index of the Task to remove.
     * @return Task The task that was removed.
     * @throws KitaOutOfBounds If the given index is out of bounds.
     */
    public Task removeTask(int index) {
        if (index >= this.tasks.size() || index < 0) {
            throw new KitaOutOfBounds();
        }

        return this.tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The size of the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Sets the task's completed status and returns the updated Task object.
     *
     * @param index The index of the task to update.
     * @param status The completed status to set.
     * @return Task The updated task.
     * @throws KitaOutOfBounds If the given index is out of bounds.
     */
    public Task setTaskCompleted(Integer index, boolean status) {
        if (index >= this.tasks.size() || index < 0) {
            throw new KitaOutOfBounds();
        }

        Task theTask = this.tasks.get(index);
        theTask.setCompleted(status);
        return theTask;
    }

    /**
     * Finds tasks that match the search query and returns them in a new TaskList.
     *
     * @param searchQuery The search query to filter tasks.
     * @return TaskList A TaskList containing the tasks that match the search query.
     */
    public TaskList find(String searchQuery) {
        TaskList returnTasks = new TaskList(new ArrayList<>());
        this.tasks.parallelStream()
                .forEachOrdered((Task task) -> {
                    if (task.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                        returnTasks.addTask(task);
                    }
                });
        return returnTasks;
    }

    @Override
    public String toString() {
        StringBuilder finalStr = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            finalStr.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return finalStr.toString();
    }
}
