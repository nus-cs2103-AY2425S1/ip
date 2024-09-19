package bob;

import java.util.ArrayList;

import exceptions.TaskIndexException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Represents a list of tasks. This class provides methods to manage tasks,
 * such as adding, marking, unmarking, and deleting tasks, as well as searching
 * for tasks by specific criteria.
 */
public class TaskList {
    private final ArrayList<Task> tasklist;

    /**
     * Constructs a new, empty {@code TaskList}.
     */
    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    /**
     * Adds a new To-Do task to the task list.
     *
     * @param name the name of the To-Do task.
     * @return the newly added {@code ToDo} task.
     */
    public ToDo todo(String name) {
        ToDo todo = new ToDo(name);
        this.tasklist.add(todo);
        return todo;
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param name the name of the Deadline task.
     * @param by the deadline date for the task.
     * @return the newly added {@code Deadline} task.
     */
    public Deadline deadline(String name, String by) {
        Deadline deadline = new Deadline(name, by);
        this.tasklist.add(deadline);
        return deadline;
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param name the name of the Event task.
     * @param from the start time of the event.
     * @param to the end time of the event.
     * @return the newly added {@code Event} task.
     */
    public Event event(String name, String from, String to) {
        Event event = new Event(name, from, to);
        this.tasklist.add(event);
        return event;
    }

    /**
     * Marks the task at the given index as complete.
     *
     * @param idx the 1-based index of the task to be marked.
     * @throws TaskIndexException if the index is out of bounds.
     */
    public void mark(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            throw new TaskIndexException(String.valueOf(idx));
        }

        this.tasklist.get(idx - 1).complete();
    }

    /**
     * Marks the task at the given index as incomplete.
     *
     * @param idx the 1-based index of the task to be unmarked.
     * @throws TaskIndexException if the index is out of bounds.
     */
    public void unmark(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            throw new TaskIndexException(String.valueOf(idx));
        }

        this.tasklist.get(idx - 1).uncomplete();
    }

    /**
     * Deletes the task at the given index from the task list.
     *
     * @param idx the 1-based index of the task to be deleted.
     * @return the deleted {@code Task}.
     * @throws TaskIndexException if the index is out of bounds.
     */
    public Task delete(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            throw new TaskIndexException(String.valueOf(idx));
        }

        Task deleted = this.tasklist.get(idx - 1);
        this.tasklist.remove(idx - 1);
        return deleted;
    }

    /**
     * Removes and returns the last task in the task list.
     *
     * @return the removed {@code Task}.
     */
    public Task pop() {
        int lastIndex = this.tasklist.size() - 1;
        Task deleted = this.tasklist.get(lastIndex);
        this.tasklist.remove(lastIndex);

        return deleted;
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task the task to be added.
     */
    public void push(Task task) {
        this.tasklist.add(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return the size of the task list.
     */
    public int getSize() {
        return this.tasklist.size();
    }

    /**
     * Returns a string describing the task at the given index.
     *
     * @param idx the 1-based index of the task to be described.
     * @return the description of the task, or an error message if the index is out of bounds.
     */
    public String describeTask(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            return "Error finding task!";
        }

        return this.tasklist.get(idx - 1).toString();
    }

    /**
     * Returns an array of strings describing all the tasks in the task list.
     *
     * @return an array of task descriptions.
     */
    public String[] describeTasks() {
        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < this.tasklist.size(); i++) {
            results.add(String.format("%d. ", i + 1) + this.tasklist.get(i).toString());
        }
        return results.toArray(new String[0]);
    }

    /**
     * Returns a string containing the save format of all tasks.
     *
     * @return a string representation of tasks in save format.
     */
    public String getSaveFormat() {
        String res = "";
        for (int i = 0; i < this.tasklist.size(); i++) {
            res += this.tasklist.get(i).getSaveFormat() + "\n";
        }
        return res;
    }

    /**
     * Finds and returns all tasks occurring on a specific date.
     *
     * @param date the date to search for tasks.
     * @return an array of task descriptions occurring on the given date.
     */
    public String[] findTasksOn(String date) {
        ArrayList<String> results = new ArrayList<>();
        this.tasklist.forEach((task) -> {
            if (task.isDuring(date)) {
                results.add(task.toString());
            }
        });
        return results.toArray(new String[0]);
    }

    /**
     * Finds tasks in the list that have names matching the given text.
     *
     * @param text the text to search for tasks.
     * @return an array of strings representing tasks that match.
     */
    public String[] findTasksWith(String text) {
        ArrayList<String> results = new ArrayList<>();
        this.tasklist.forEach((task) -> {
            if (task.hasInName(text)) {
                results.add(task.toString());
            }
        });
        return results.toArray(new String[0]);
    }

    /**
     * Gets a string representation of all tasks in the list.
     *
     * @return a string where each line represents a task with its index.
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < this.tasklist.size(); i++) {
            str += String.format("%d. ", i + 1) + this.tasklist.get(i) + "\n";
        }
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList temp) {
            return this.tasklist.equals(temp.tasklist);
        }
        return false;
    }
}
