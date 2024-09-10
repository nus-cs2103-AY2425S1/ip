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
    public String addToList(Task task) {
        this.taskList.add(task);
        return String.format("Got it. I have added this task:\n  %s\nNow you have %d task in the list\n",
                task, this.taskList.size());
    }

    /**
     * Marks a task as completed and updates the storage file accordingly.
     *
     * @param i The index of the task to be marked as completed.
     * @param storage The Storage object used to update the task's status in the respective file.
     * @return A String message confirming that the task has been marked as completed.
     */
    public String markTask(int i, Storage storage) {
        Task task = this.taskList.get(i);
        String oldLine = task.toStringinFile();
        task.completeTask();
        String newLine = task.toStringinFile();
        storage.editTask(oldLine, newLine);
        return "Nice! I have marked this task as completed:\n " + task;
    }

    /**
     * Unmarks a task as incomplete and updates the storage file accordingly.
     *
     * @param i The index of the task to be marked as uncompleted.
     * @param storage The Storage object used to update the task's status in the respective file.
     * @return A String message confirming that the task has been marked as uncompleted.
     */
    public String unmarkTask(int i, Storage storage) {
        Task task = this.taskList.get(i);
        String oldLine = task.toStringinFile();
        task.incompleteTask();
        String newLine = task.toStringinFile();
        storage.editTask(oldLine, newLine);
        return "Ok! I have marked this task as incomplete:\n " + task;
    }

    /**
     * Deletes a task from the list and updates the storage file accordingly.
     *
     * @param i The index of the task to be deleted.
     * @param storage The Storage object used to remove the task from the respective file.
     * @return A String message confirming that the task has been removed and the current number of tasks in the list.
     */
    public String delTask(int i, Storage storage) {
        Task task = this.taskList.remove(i);
        storage.delTaskFromStorage(task.toStringinFile());
        return "Noted! I have removed this task:\n " + task
                + String.format("Now you have %d task in the list%n", this.taskList.size());
    }


    public String filterTasks(String filter) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i).toString().toLowerCase().contains(filter)) {
                filteredList.add(this.taskList.get(i));
            }
        }
        ArrayList<Task> filteredList2 = (ArrayList<Task>) this.taskList.stream().filter(x->x.toString().toLowerCase().contains(filter)).toList();
        if (filteredList.isEmpty()) {
            return "You have no items in your list matching the given string";
        } else {
            String str = "";
            str += "\nHere are the items in your list that match the search:\n";
            for (int i = 0; i < filteredList.size(); i++) {
                str += (i + 1) + ". " + filteredList.get(i) + "\n";
            }
            return str;
        }

    }

    /**
     * Returns total number of tasks in the list.
     *
     * @return The total number of tasks in the list.
     */

    public int size() {
        return this.taskList.size();
    }
    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "You have no items in your list:";
        } else {
            String str = "";
            str += "Here are the items in your list:\n";
            for (int i = 0; i < this.taskList.size(); i++) {
                str += (i + 1) + ". " + this.taskList.get(i) + "\n";
            }
            return str;
        }
    }
}
