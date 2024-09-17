package seedu.maxine;

import seedu.maxine.task.Task;

import java.util.Iterator;

public interface MaxineList extends Iterable<Task> {
    public void addTask(Task task);
    public void delete(int key);
    public int size();
    public Task get(int num);
    public Iterator<Task> iterator();
    public void deleteAll();
}
