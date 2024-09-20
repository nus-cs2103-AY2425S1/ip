package reo.tasks;

import java.util.ArrayList;

/** Manages the list of tasks stored */
public class TaskList {
    /** The current list of tasks based on user input */
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    /**
     * Deletes the specified task at index taskNumber (0-indexed) in the ArrayList.
     *
     * @param indexes The task indexes to be deleted.
     */
    public void deleteTasks(ArrayList<Integer> indexes) {
        for (int i = indexes.size() - 1; i >= 0; i--) {
            int index = indexes.get(i);
            tasks.remove(index);
        }
    }

    /**
     * Marks the specified task at index taskNumber (0-indexed) in the ArrayList, as done.
     *
     * @param taskNumber The task to be marked as done.
     */
    public void markTask(int taskNumber) {
        Task toMark = tasks.get(taskNumber);
        toMark.mark();
    }

    /**
     * Marks the specified task at index taskNumber (0-indexed) in the ArrayList, as undone.
     *
     * @param taskNumber The task to be marked as undone.
     */
    public void unmarkTask(int taskNumber) {
        Task toUnmark = tasks.get(taskNumber);
        toUnmark.unmark();
    }

    /**
     * Adds the specified reo.tasks.Todo object to the ArrayList.
     *
     * @param t The task to be added.
     */
    public void addTodo(Todo t) {
        assert t != null : "Todo task should not be null";
        tasks.add(t);
    }

    /**
     * Adds the specified reo.tasks.Deadline object to the ArrayList.
     *
     * @param d The task to be added.
     */
    public void addDeadline(Deadline d) {
        assert d != null : "Deadline task should not be null";
        tasks.add(d);
    }

    /**
     * Adds the specified reo.tasks.Event object to the ArrayList.
     *
     * @param e The task to be added.
     */
    public void addEvent(Event e) {
        assert e != null : "Event task should not be null";
        tasks.add(e);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Gets the size of the stored ArrayList so far.
     *
     * @return The size of the current ArrayList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the String representation of the task
     * at the specified index (0-indexed) of the ArrayList.
     *
     * @param taskNumber Index of the task of interest.
     * @return The String representation at index i.
     */
    public String getStringAt(int taskNumber) {
        assert taskNumber >= 0 && taskNumber < tasks.size() : "Invalid task number for getting string";
        return tasks.get(taskNumber).toString();
    }

    /**
     * Gets the Task object
     * at the specified index (0-indexed) of the ArrayList.
     *
     * @param taskNumber Index of the task of interest.
     * @return The Task object at index i.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Filters the current task list.
     *
     * @param s The keyword to search for.
     * @return A new ArrayList, which only contains tasks whose names
     * contain the keyword s.
     */
    public ArrayList<Task> filter(String s) {
        assert s != null && !s.isEmpty() : "Search keyword should not be null or empty";
        ArrayList<Task> copy = new ArrayList<>();
        copy.addAll(tasks);
        copy.removeIf(t -> !t.nameDoesContain(s));
        return copy;
    }

    /**
     * Creates the String representation of the stored ArrayList so far.
     *
     * @return The String representation of the stored ArrayList so far.
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            int listIndex = i + 1;
            res += listIndex + ". " + tasks.get(i).toString() + "\n";
        }

        return res;
    }
}
