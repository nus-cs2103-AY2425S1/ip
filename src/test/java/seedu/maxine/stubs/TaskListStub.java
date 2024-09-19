package seedu.maxine.stubs;

import java.util.ArrayList;
import java.util.Iterator;

import seedu.maxine.task.MaxineList;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

/**
 * Manages the storage of tasks in a file.
 * <p>
 * The Storage class provides functionality to load tasks from a file,
 * refresh the file with updated task lists, and query tasks based on a search keyword.
 * </p>
 */
public class TaskListStub implements MaxineList {
    private ArrayList<Task> list;

    public TaskListStub() {
        // nothing
    }
    @Override
    public void addTask(Task task) {
        // nothing
    }
    @Override
    public void delete(int key) {
        // nothing
    }
    @Override
    public int size() {
        return 1;
    }
    @Override
    public Task getTask(int num) {
        return new Todo("test command class");
    }
    @Override
    public Iterator<Task> iterator() {
        return null;
    }
    @Override
    public void deleteAll() {
        // nothing
    }
}
