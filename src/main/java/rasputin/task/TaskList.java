package rasputin.task;

import rasputin.command.Command;
import rasputin.command.Undoable;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the form of an ArrayList.
 *
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected Command lastCommand;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns whether the TaskList is empty.
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the TaskList.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the task at the given index.
     *
     * @param index of task to be retrieved.
     * @return Task
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index of task to be marked.
     */
    public void mark(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index of task to be unmarked
     */
    public void unmark(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Adds the task to the TaskList.
     *
     * @param task to be added.
     */
    public void add(Task task) {
        assert task != null : "Tasks cannot be null";
        tasks.add(task);
    }

    /**
     * Removes the task from the TaskList.
     *
     * @param index of task to be removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Size of list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Sets the most recent command that can be undone.
     *
     * @param command Command to be set as the most recent command.
     */
    public void setLastCommand(Command command) {
        this.lastCommand = command;
    }

    /**
     * Sets the most recent command to be null.
     *
     * @return Most recent command to be undone.
     */
    public Undoable removeLastCommand() {
        Undoable commandToUndo = (Undoable) lastCommand;
        lastCommand = null;
        return commandToUndo;
    }

    /**
     * Returns the contents of the list to be printed out in the UI.
     * If list is empty, return "No tasks in list!".
     *
     * @return String format of the list.
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "No tasks in list!";
        }
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += String.format("%d.%s", i + 1, tasks.get(i));
            if (i < tasks.size() - 1) {
                str += "\n";
            }
        }
        return str;
    }
}
