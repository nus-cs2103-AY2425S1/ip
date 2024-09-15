package spongebob.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import spongebob.exception.SpongebobException;
import spongebob.task.Task;

/**
 * Storage class to keep tasks for the user
 */
public class TaskList {

    private List<Task> cache;

    /**
     * Constructs a new empty tasklist
     */
    public TaskList() {
        this.cache = new ArrayList<>();
    }

    /**
     * Constructs a tasklist using a storage module
     * @param storage Data obtained from text file
     * @throws SpongebobException text file is not found
     */
    public TaskList(Storage storage) throws SpongebobException {
        this.cache = storage.load();
    }

    /**
     * Returns a list of tasks currently loaded
     * @return list of tasks
     */
    public List<Task> getCache() {
        return this.cache;
    }

    /**
     * Updates a task by replacing it with a new task
     * @param index the index of task to be updated
     * @param task  The new task to replace the original tasj
     * @throws IndexOutOfBoundsException Index given is not within range of the tasklist.
     */
    public void update(int index, Task task) throws IndexOutOfBoundsException {
        cache.set(index, task);
    }

    /**
     * Adds a task to the end of tasklist
     * @param task  new task to be added.
     */
    public void add(Task task) {
        this.cache.add(task);
    }

    /**
     * Deletes the task in tasklist in given index
     * @param index index of task to be deleted
     */
    public void delete(int index) {
        this.cache.remove(index);
    }

    /**
     * Returns the length of the tasklist
     * @return length of tasklist
     */
    public int size() {
        return this.cache.size();
    }

    /**
     * Display list in index order such as 1.[T][ ] return books
     * @return String form of the list of tasks in index order
     */
    @Override
    public String toString() {
        ListIterator<Task> iter = this.cache.listIterator();
        StringBuilder listInStringBuilder = new StringBuilder();

        while (iter.hasNext()) {
            Task cur = iter.next();
            listInStringBuilder.append((iter.previousIndex() + 1) + "." + cur + "\n");
        }

        return listInStringBuilder.toString();
    }

    /**
     * Returns the list of tasks whose description fits the keyword given
     *
     * @param keyword A keyword for filtering out tasks
     * @return List of Tasks that fits the keyword
     */

    public List<Task> find(String keyword) {
        List<Task> res = new ArrayList<>();
        for (Task task : cache) {
            if (task.contains(keyword)) {
                res.add(task);
            }
        }
        return res;
    }
}
