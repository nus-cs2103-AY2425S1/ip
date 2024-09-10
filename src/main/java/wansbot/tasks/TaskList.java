package wansbot.tasks;

import java.util.ArrayList;

/**
 * Class which allows tasks to be put into a list.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Initializes an ArrayList of Tasks.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to TaskList's listOfTasks.
     */
    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * Marks a task in listOfTasks as complete.
     */
    public void finish(int index) {
        listOfTasks.get(index).finish();
    }

    /**
     * Marks a task in listOfTasks as incomplete.
     */
    public void unfinish(int index) {
        listOfTasks.get(index).unfinish();
    }

    /**
     * Returns the size of listOfTasks.
     */
    public int numOfTasks() {
        return this.listOfTasks.size();
    }

    /**
     * Removes a specified task in listOfTasks.
     */
    public Task removeTask(int taskNum) {
        Task task = this.listOfTasks.get(taskNum);
        this.listOfTasks.remove(taskNum);
        return task;
    }

    /**
     * Returns the task at a particular index on listOfTasks.
     */
    public Task getTask(int i) {
        return this.listOfTasks.get(i);
    }

    @Override
    public String toString() {
        if (this.listOfTasks.size() <= 0) {
            return "Maybe you can try adding tasks :)";
        }
        String myTasks = "";
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            myTasks += (i + 1) + ". " + this.listOfTasks.get(i).toString() + "\n";
        }

        return myTasks;
    }
}
