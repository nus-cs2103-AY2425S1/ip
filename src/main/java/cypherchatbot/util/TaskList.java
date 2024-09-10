package cypherchatbot.util;
import java.util.ArrayList;

import cypherchatbot.task.Task;



/**
 * The TaskList class manages a ArrayList of Task objects, providing
 * methods to add, remove, and manipulate tasks in the list.
 */

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Overloaded constructor that creates a TaskList class with an existing list of tasks.
     *
     * @param taskList The list of tasks to be managed by this instance of TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Overloaded constructor that creates a TaskList class with an empty list of tasks.
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }


    /**
     * Add a new task to the list.
     *
     * @param task The new Task to be added to the list.
     * @return A String message confirming the addition of the task and indicates total number of tasks in the list.
     */
    public void addToList(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks a task as completed and updates the storage file accordingly.
     *
     * @param i The index of the task to be marked as completed.
     * @param storage The Storage object used to update the task's status in the respective file.
     * @return A String message confirming that the task has been marked as completed.
     */
    public Task markTask(int i, Storage storage) {
        Task task = this.taskList.get(i);
        String oldLine = task.toStringInFile();
        task.markAsComplete();
        String newLine = task.toStringInFile();
        storage.editTask(oldLine, newLine);
        return task;
    }

    /**
     * Unmarks a task as incomplete and updates the storage file accordingly.
     *
     * @param i The index of the task to be marked as uncompleted.
     * @param storage The Storage object used to update the task's status in the respective file.
     * @return A String message confirming that the task has been marked as uncompleted.
     */
    public Task unmarkTask(int i, Storage storage) {
        Task task = this.taskList.get(i);
        String oldLine = task.toStringInFile();
        task.markAsIncomplete();
        String newLine = task.toStringInFile();
        storage.editTask(oldLine, newLine);

        return task;
    }

    /**
     * Deletes a task from the list and updates the storage file accordingly.
     *
     * @param i The index of the task to be deleted.
     * @param storage The Storage object used to remove the task from the respective file.
     * @return A String message confirming that the task has been removed and the current number of tasks in the list.
     */
    public Task delTask(int i, Storage storage) {
        Task task = this.taskList.remove(i);
        storage.delTaskFromStorage(task.toStringInFile());
        return task;
    }


    public ArrayList<Task> filterTasks(String filter) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).toString().toLowerCase().contains(filter)) {
                filteredList.add(this.taskList.get(i));
            }
        }

        return filteredList;
    }

    /**
     * Returns total number of tasks in the list.
     *
     * @return The total number of tasks in the list.
     */

    public int size() {
        return this.taskList.size();
    }
    public ArrayList<Task> getList() {
        return this.taskList;
    }
}
