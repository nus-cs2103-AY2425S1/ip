package bob;

import java.util.ArrayList;

/**
 * The {@code TaskList} encapsulates a 1-indexed list of tasks for the Bob application.
 * Implemented using an {@code ArrayList}.
 * It allows for operations such as adding, marking, unmarking, deleting, and describing tasks as strings.
 */
public class TaskList {
    private final ArrayList<Task> tasklist;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    /**
     * Adds a new {@code ToDo} task to the list.
     *
     * @param name the name of the task.
     * @return the created {@code ToDo} task.
     */
    public ToDo todo(String name) {
        ToDo todo = new ToDo(name);
        this.tasklist.add(todo);
        return todo;
    }

    /**
     * Adds a new {@code Deadline} task to the list.
     *
     * @param name the name of the task.
     * @param by the deadline date.
     * @return the created {@code Deadline} task.
     */
    public Deadline deadline(String name, String by) {
        Deadline deadline = new Deadline(name, by);
        this.tasklist.add(deadline);
        return deadline;
    }

    /**
     * Adds a new {@code Event} task to the list.
     *
     * @param name the description of the task.
     * @param from the start date of the event.
     * @param to the end date of the event.
     * @return the created {@code Event} task.
     */
    public Event event(String name, String from, String to) {
        Event event = new Event(name, from, to);
        this.tasklist.add(event);
        return event;
    }

    /**
     * Marks a task as completed based on its 1-indexed index.
     *
     * @param idx the index of the task to be marked.
     * @throws TaskIndexException if the index is out of range.
     */
    public void mark(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            throw new TaskIndexException(String.valueOf(idx));
        }

        this.tasklist.get(idx-1).complete();
    }

    /**
     * Unmarks a task as incomplete based on its 1-indexed index.
     *
     * @param idx the index of the task to be unmarked.
     * @throws TaskIndexException if the index is out of range.
     */
    public void unmark(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            throw new TaskIndexException(String.valueOf(idx));
        }

        this.tasklist.get(idx-1).uncomplete();
    }

    /**
     * Deletes a task from the list based on its 1-indexed index.
     *
     * @param idx the index of the task to be deleted.
     * @return the deleted {@code Task}.
     * @throws TaskIndexException if the index is out of range.
     */
    public Task delete(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            throw new TaskIndexException(String.valueOf(idx));
        }

        Task deleted = this.tasklist.get(idx-1);
        this.tasklist.remove(idx-1);
        return deleted;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return this.tasklist.size();
    }

    /**
     * Describes a task based on its 1-indexed index.
     * The description includes the task's name, dates, and completion status.
     *
     * @param idx the index of the task to describe.
     * @return a string representation of the task.
     * @throws TaskIndexException if the index is out of range.
     */
    public String describeTask(int idx) {
        if (idx < 1 || idx > this.tasklist.size()) {
            throw new TaskIndexException(String.valueOf(idx));
        }

        return this.tasklist.get(idx-1).toString();
    }

    /**
     * Describes all tasks in the list.
     * The description includes the task's name, dates, and completion status.
     *
     * @return an array of strings, each representing a task in the list.
     */
    public String[] describeTasks() {
        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < this.tasklist.size(); i++) {
            results.add(String.format("%d. ", i + 1) + this.tasklist.get(i).toString());
        }
        return results.toArray(new String[0]);
    }

    /**
     * Gets a string representation of all tasks in the list formatted for saving.
     *
     * @return a string representing the tasks in a format suitable for saving to a file.
     */
    public String getSaveFormat() {
        String res = "";
        for (int i = 0; i < this.tasklist.size(); i++) {
            res += this.tasklist.get(i).getSaveFormat() + "\n";
        }
        return res;
    }

    /**
     * Finds tasks in the list that occur on a specific date.
     *
     * @param date the date to search for tasks.
     * @return an array of strings representing tasks that occur on the specified date.
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

    /**
     * Compares this {@code TaskList} with another object for equality.
     * Implemented for testing purposes.
     *
     * @param obj the object to compare with this {@code TaskList}.
     * @return {@code true} if the specified object is equal to this {@code TaskList}; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TaskList temp) {
            return this.tasklist.equals(temp.tasklist);
        }
        return false;
    }
}
