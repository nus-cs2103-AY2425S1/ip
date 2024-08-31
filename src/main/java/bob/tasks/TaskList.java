package bob.tasks;

import bob.exceptions.InvalidTaskNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList stores a list of Task objects
 */
public class TaskList {
    private final List<Task> taskList;

    /**
     * Constructor for TaskList
     * Initializes the taskList attribute to be a new ArrayList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the taskList
     *
     * @param task Task to be added into the taskList
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the task at the specified index from the task list
     *
     * @param index Index at which the task to be removed is at
     * @return Task that was removed
     * @throws InvalidTaskNumberException If the index is out of range of the task list or < 0
     */
    public Task removeTaskAtIndex(int index) throws InvalidTaskNumberException {
        if (this.size() <= index || index < 0) {
            throw new InvalidTaskNumberException();
        }
        return this.taskList.remove(index);
    }

    /**
     * Get the size of the taskList
     *
     * @return the size of the taskList
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Marks the task at the specified index as completed
     *
     * @param index Index at which the task to be marked is at
     * @throws InvalidTaskNumberException If the index is out of range of the task list or < 0
     */
    public void markTaskAtIndex(int index) throws InvalidTaskNumberException {
        if (this.size() <= index || index < 0) {
            throw new InvalidTaskNumberException();
        }
        this.taskList.get(index).mark();
    }

    /**
     * Unmarks the task at the specified index as uncompleted
     *
     * @param index Index at which the task to be unmarked is at
     * @throws InvalidTaskNumberException If the index is out of range of the task list or < 0
     */
    public void unmarkTaskAtIndex(int index) throws InvalidTaskNumberException {
        if (this.size() <= index || index < 0) {
            throw new InvalidTaskNumberException();
        }
        this.taskList.get(index).unmark();
    }

    /**
     * Export all the tasks in the taskList to string to be saved in a text file
     *
     * @return String format of all the tasks in the taskList to be exported
     */
    public String export() {
        return this.taskList.stream().map(Task::export)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Converts all the tasks in the taskList to a string to be printed
     *
     * @return String format of all the tasks in the taskList for printing
     */
    @Override
    public String toString() {
        String taskString = " Here are the tasks in your list:";
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            taskString = taskString + "\n " + i + "." + this.taskList.get(i-1);
        }
        return taskString;
    }
}
