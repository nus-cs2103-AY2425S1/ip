package broski.taskrelated;

import java.util.ArrayList;

import broski.task.Task;

/**
 * Task that stores all functionality related to the tasklist.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a new TaskList.
     * @param list the ArrayList that TaskList houses.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Returns size of the list.
     * @return size of list
     */
    public int size() {
        return list.size();
    }

    /**
     * Same functionality as ArrayList get.
     * @param index index of task to be returned from list.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Same functionality as ArrayList add.
     * @param task task to be added to list.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Same functionality as ArrayList remove.
     * @param index index of task to be removed from list.
     */
    public void remove(int index) {
        list.remove(index);
    }
}
