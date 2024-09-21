package myapp.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * The TaskList class represents a list of tasks. It provides methods to add, delete, and retrieve tasks,
 * as well as to check the list's size and whether it is empty. This class also implements {@link Iterable}, allowing
 * it to be used in enhanced for-loops and other contexts where an {@link Iterator} is required.
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the tasks from the given list.
     *
     * @param storage the list of tasks to initialize this TaskList with.
     */
    public TaskList(List<Task> storage) {
        this.taskList = storage;
    }

    /**
     * Adds a task to the list.
     *
     * @param t the {@link Task} to be added.
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index the index of the task to be deleted.
     * @return the {@link Task} that was removed from the list.
     */
    public Task delete(int index) {
        return taskList.remove(index);
    }

    /**
     * Checks if the list is empty.
     *
     * @return {@code true} if the list contains no tasks, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param i the index of the task to retrieve.
     * @return the {@link Task} at the specified index.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list, or -1 if the list
     *      does not contain the element.
     *
     * @param o the element to search for.
     * @return the index of the first occurrence of the specified element, or -1 if not found.
     */
    public int indexOf(Object o) {
        return taskList.indexOf(o);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns a stream of tasks in the list, providing flexibility for task processing.
     *
     * @return a {@link Stream} of tasks in this list.
     */
    public Stream<Task> stream() {
        return taskList.stream();
    }

    /**
     * Returns an iterator over the tasks in this list.
     *
     * @return an {@link Iterator} over the tasks in this list.
     */
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
