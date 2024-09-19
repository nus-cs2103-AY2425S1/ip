package snowy.tasklist;

import snowy.data.SnowyException;
import snowy.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;
    private final Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        taskList = new ArrayList<>();
    }

    public void loadStorage() throws SnowyException {
        try {
            storage.loadTasksFromFile(taskList);
        } catch (SnowyException e) {
            throw new SnowyException("Error loading files: " + e.getMessage());
        }
    }

    /**
     * Adds a task to the task list and saves it to the file.
     *
     * @param t the task to be added
     */
    public String addTask(Task t) throws SnowyException {
        taskList.add(t);
        storage.writeTaskToFile(t);
        return String.format(" %s\nNow you have %d task(s) in your list\n", t, this.taskList.size());
    }

    public boolean isTaskDone(int i) throws SnowyException {
        if (i < 0 || i >= taskList.size()) {
            throw new SnowyException("Invalid index.");
        }
        Task task = taskList.get(i);
        return task.isDone;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Toggles the completion status of the task at the specified index.
     *
     * @param i the index of the task to toggle
     * @throws SnowyException if the provided index is invalid
     */
    public String toggleTask(int i) throws SnowyException {
        if (i < 0 || i >= taskList.size()) {
            throw new SnowyException("Invalid index.");
        }
        assert i >= 0 || i < taskList.size();
        Task task = taskList.get(i);
        task.toggleStatus();
        return String.format("%d. %s", i + 1, task);

    }

    /**
     * Tags task at the specified index.
     *
     * @param i the index of the task to toggle
     * @throws SnowyException if the provided index is invalid
     */
    public String tagTask(int i, String tag) throws SnowyException {
        if (i < 0 || i >= taskList.size()) {
            throw new SnowyException("Invalid index.");
        }
        Task task = taskList.get(i);
        String before = task.toString();
        task.addTag(tag);
        String after = task.toString();
        storage.editTask(before, after);

        return String.format("%d. %s", i + 1, task);

    }

    /**
     * Displays the list of tasks, each prefixed with its index in the list.
     *
     * @throws SnowyException if the task list is empty
     */
    public String displayList() throws SnowyException {
        if (taskList.isEmpty()) {
            throw new SnowyException("No tasks, make a list first.");
        }
        assert taskList.size() > 0;
        StringBuilder str = new StringBuilder("Your list of tasks:\n");
        for (int i = 0; i < taskList.size(); i++) {
            str.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }
        return str.toString();
    }

    /**
     * Deletes the task at the specified index from the task list and the file.
     *
     * @param index the index of the task to delete (1-based index)
     * @throws SnowyException if the index is invalid or the task list is empty
     */
    public String deleteTask(int index) throws SnowyException {
        if (taskList.isEmpty()) {
            throw new SnowyException("No tasks in list.");
        }
        if (index >= 1 && index <= taskList.size()) {
            Task task = taskList.get(index - 1);
            String str = String.format("Removed task:\n " + task);
            taskList.remove(index - 1);
            storage.deleteTaskFromFile(task);
            return str + String.format("\nNow you have %d task(s) in your list.\n", this.taskList.size());
        } else {
            assert index < 1 || index > taskList.size();
            throw new SnowyException("Invalid index.");
        }
    }
}
