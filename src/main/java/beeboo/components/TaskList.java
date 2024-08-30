package beeboo.components;

import beeboo.task.Tasks;

import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks. It provides methods to add, remove, and modify tasks in the list.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    ArrayList<Tasks> list;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list the initial list of tasks
     */
    public TaskList(ArrayList<Tasks> list) {
        this.list = list;
    }

    /**
     * Constructs an empty TaskList and creates the necessary directory for storage.
     */
    public TaskList() {
        list = new ArrayList<>();
        Storage.createFile();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
    public void addList(Tasks task) {
        list.add(task);
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index the index of the task to be deleted
     * @return the task that was removed from the list
     */
    public Tasks deleteItem(int index) {
        Tasks item = list.get(index);
        list.remove(item);
        return item;
    }

    /**
     * Marks a task as not done at the specified index.
     *
     * @param index the index of the task to be unmarked
     * @return the task that was unmarked
     */
    public Tasks unmarkDone(int index) {
        Tasks task = list.get(index);
        task.unmarkDone();
        return task;
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index the index of the task to be marked
     * @return the task that was marked
     */
    public Tasks markDone(int index) {
        Tasks task = list.get(index);
        task.markDone();
        return task;
    }

    /**
     * Produces a string representation of the task list with each task numbered.
     *
     * @return a string representing the list of tasks
     */
    public String produceList() {
        String result = "";
        for (Tasks task : list) {
            result = result + (list.indexOf(task) + 1) + ". " + " " + task + "\n";
        }
        return result;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns task of specified index.
     *
     * @return Tasks
     */
    public Tasks get(int index) {
        return list.get(index);
    }
}
