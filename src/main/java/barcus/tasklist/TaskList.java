package barcus.tasklist;

import barcus.task.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to encapsulate the list of tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int length;

    /**
     * Constructor for Tasklist, creates an empty list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.length = 0;
    }

    /**
     * Constructor for Tasklist, creates list based on input list
     * @param tasks input tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.length = this.tasks.size();
    }

    /**
     * Displays tasks in lists
     */
    public void showTaskList() {
        for (int i = 0; i < this.length; i++) {
            System.out.println(String.valueOf(i + 1) + ". " + tasks.get(i).toString());
//            System.out.println(i);
        }
//        System.out.println(tasks);
    }

    /**
     * Converts into savable string
     * @return string of tasklist
     */
    public String convertToSavable() {
        List<String> temp = new ArrayList<>();
        this.tasks.forEach( task -> temp.add(task.convertToSavedString() + "\n"));
        return String.join("", temp);
    }

    /**
     * Returns length of TaskList
     * @return length of TaskList
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Adds t into list
     * @param t task
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        this.length++;
    }

    /**
     * Returns string representation of task at index i
     * @param i index
     * @return string of task
     */
    public String getTaskString(int i) {
        return this.tasks.get(i).toString();
    }

    /**
     * Unmarks task at index i
     * @param i index
     */
    public void unmarkTask(int i) {
        this.tasks.get(i).unmarkDone();
    }

    /**
     * Marks task at index i
     * @param i index
     */
    public void markTask(int i) {
        this.tasks.get(i).markDone();
    }

    /**
     * Deletes task at index i
     * @param i index
     * @return deleted index
     */
    public Task deleteTask(int i) {
        this.length--;
        return this.tasks.remove(i);
    }
}
