package task;

import java.util.ArrayList;

import exceptions.TaskIndexOutOfBound;

/**
 * A stub class for TaskList used in testing.
 * This class simulates the behavior of TaskList without actual storage operations.
 */
public class TaskListStub extends TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a new TaskListStub with an initial task list.
     * @param taskList The initial task list.
     */
    public TaskListStub(ArrayList<Task> taskList) {
        super(taskList);
    }

    /**
     * Adds a task to the list.
     * @param task The task to add.
     */
    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task at the specified index.
     * @param index The index of the task to delete.
     * @throws TaskIndexOutOfBound if the index is out of bounds.
     */
    @Override
    public void deleteTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.remove(index);
    }

    /**
     * Marks a task as done at the specified index.
     * @param index The index of the task to mark as done.
     * @throws TaskIndexOutOfBound if the index is out of bounds.
     */
    @Override
    public void markTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.get(index).setMarkStatus(true);
    }

    /**
     * Unmarks a task as not done at the specified index.
     * @param index The index of the task to unmark.
     * @throws TaskIndexOutOfBound if the index is out of bounds.
     */
    public void unmarkTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        tasks.get(index).setMarkStatus(false);
    }

    /**
     * Retrieves a task at the specified index.
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws TaskIndexOutOfBound if the index is out of bounds.
     */
    @Override
    public Task getTask(int index) throws TaskIndexOutOfBound {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBound();
        }
        return tasks.get(index);
    }

    /**
     * Retrieves the entire task list.
     * @return The list of tasks.
     */
    @Override
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
