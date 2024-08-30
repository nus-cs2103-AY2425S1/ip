package tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> list;

    public TaskList(List<Task> loadedData) {
        this.list = loadedData;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds the specified task to the task list.
     *
     * @param task Task to be added.
     * @param saved True if the task to added should be pre-marked as done, otherwise False.
     */
    public void add(Task task, boolean saved) {
        this.list.add(task);
        if (!saved) {
            System.out.println("I've added the task: ");
            System.out.println(task);
            System.out.println("Now you have " + this.list.size() + " tasks in the list");
        }
    }

    /**
     * Lists out all the tasks in the task list.
     */
    public void listOut() {
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println(i + "." + this.list.get(i - 1));
        }
    }

    /**
     * Marks the specified task as done.
     *
     * @param index Index of task to be marked as done.
     */
    public void markTask(int index) {
        this.list.get(index).mark();
        System.out.println("You have marked the following task as done!");
        System.out.println(this.list.get(index));
    }

    /**
     * Marks the specified task as undone.
     *
     * @param index Index of task to be marked as done.
     */
    public void unmarkTask(int index) {
        this.list.get(index).unmark();
        System.out.println("You have unmarked the following task!");
        System.out.println(this.list.get(index));
    }

    /**
     * Deletes the specified task.
     *
     * @param index Index of task to be deleted.
     */
    public void delete(int index) {
        Task deleted = this.list.get(index);
        this.list.remove(index);
        System.out.println("Let's go deleting!");
        System.out.println("Deleted task " + deleted);
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.list;
    }
}
