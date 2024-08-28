package Kita;

import Kita.Exceptions.KitaIllegalDateFormat;
import Kita.Exceptions.KitaOutofBounds;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the ArrayList<Task> stored in this TaskList object
     *
     * @return ArrayList<Task>
     * */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Adds a given Task object
     *
     * @param taskObj - The Task to add
     * @return void
     * */
    public void addTask(Task taskObj) {
        this.tasks.add(taskObj);
    }

    /**
     * Returns a Task object given an int Index
     *
     * @param index Int index to return a task from
     * @return Task - The task to get
     * */
    public Task getTask(Integer index) {
        if (index >= this.tasks.size() || index < 0) {
            throw new KitaOutofBounds();
        }

        return this.tasks.get(index);
    }

    /**
     * Removes a task and returns it
     *
     * @param index Int index to remove the task
     * @exception KitaOutofBounds If the given index is out of bounds of the TaskList
     * @return Task - The task that was removed
     * */
    public Task removeTask(int index) {
        if (index >= this.tasks.size() || index < 0) {
            throw new KitaOutofBounds();
        }

        return this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Sets the task's completed status and returns it
     *
     * @param index Int index of the task to target
     * @param status boolean to change the status to
     * @exception KitaOutofBounds If the given index is out of bounds of the TaskList
     * @return Task - The task that was removed
     * */
    public Task setTaskCompleted(Integer index, boolean status) {
        if (index >= this.tasks.size() || index < 0) {
            throw new KitaOutofBounds();
        }

        Task theTask = this.tasks.get(index);
        theTask.setCompleted(status);
        return theTask;
    }

    public TaskList find(String searchQuery) {
        TaskList returnTasks = new TaskList(new ArrayList<>());
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getName().toLowerCase()
                    .contains(
                            searchQuery.toLowerCase()
                    )
            ) {
                returnTasks.addTask(this.tasks.get(i));
            }
        }
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
