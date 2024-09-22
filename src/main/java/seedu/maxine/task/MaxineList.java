package seedu.maxine.task;

import java.util.Iterator;

/**
 * The MaxineList interface defines the methods for managing a list of tasks.
 * It allows for adding, deleting, and retrieving tasks, as well as
 * providing an iterator for iterating over the tasks.
 */
public interface MaxineList extends Iterable<Task> {
    void addTask(Task task);
    void delete(int key);
    int size();
    Task getTask(int num);
    Iterator<Task> iterator();
    void deleteAll();
}
