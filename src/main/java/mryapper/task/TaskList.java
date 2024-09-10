package mryapper.task;

import mryapper.storagemanager.StorageManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains an ArrayList of tasks and all the necessary functions for the task list.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Creates an empty task list with the capacity of 100 tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Creates an empty task list containing the tasks in the given ArrayList.
     *
     * @param taskList An ArrayList of Task to be contained by the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Remove the task from the list with the given index.
     *
     * @param taskNumber The index of the task to remove starting from 1.
     * @return The task that has been removed.
     */
    public Task remove(int taskNumber) {
        return taskList.remove(taskNumber - 1);
    }

    /**
     * Counts the number of task in the list.
     *
     * @return The number of task in the list.
     */
    public int count() {
        return this.taskList.size();
    }

    /**
     * Marks the task with the given index as done.
     *
     * @param taskNumber The index of the task starting from 1.
     * @return The task that has been marked as done.
     */
    public Task mark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task with the given index as not done.
     *
     * @param taskNumber The index of the task starting from 1.
     * @return The task that has been marked as not done.
     */
    public Task unmark(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsNotDone();
        return task;
    }

    /**
     * Saves the current list of tasks into the given storage.
     *
     * @param storage The storage to save the list of tasks to.
     */
    public void saveToStorage(StorageManager storage) {
        try {
            storage.saveTasks(this.taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong when saving your changes to the task list :(");
        }
    }

    /**
     * Searches and returns an ArrayList of task containing all the keywords in the string.
     *
     * @param str Search input for task descriptions.
     * @return An ArrayList containing task with all the keywords in their description.
     */
    public ArrayList<Task> searchTasks(String str) {
        ArrayList<Task> result = new ArrayList<>(100);
        for (int i = 0; i < this.count(); i++ ) {
            Task task = this.taskList.get(i);
            if (task.hasKeywords(str)) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        int listSize = this.count();
        if (listSize == 0) {
            return "You do not have any tasks!";
        }

        String listInString = "";
        for (int i = 0; i < listSize; i += 1) {
            String taskString = String.format("%d.%s", i + 1, taskList.get(i));
            listInString += taskString;
            if (i < listSize - 1) {
                listInString += "\n";
            }
        }
        return listInString;
    }
}
