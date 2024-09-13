package seedu.maxine;

import seedu.maxine.exception.MaxineException;
import seedu.maxine.task.Task;

public interface MaxineList extends Iterable<Task> {
    //TODO: Change these to java docs
    // Method to add a Todo task
    void addTask(Task task);

    // Method to delete a task
    void delete(int task);

    // Method to get the size of the list
    int size();

    // Method to get a task at a specific index
    Task get(int num);
}
