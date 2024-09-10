package broski.taskrelated;

import java.util.ArrayList;

import broski.task.Task;

/**
 * Stores all functionality related to the archiving of tasks.
 */
public class Archive {
    private ArrayList<Task> list;

    /**
     * Constructs a new Archive.
     * @param list the ArrayList that TaskList houses.
     */
    public Archive(ArrayList<Task> list) {
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
}
