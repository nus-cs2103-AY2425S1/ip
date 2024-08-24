package reo;

import reo.Deadline;
import reo.Event;
import reo.Task;

import java.util.ArrayList;

public class TaskList {
    /** The current list of tasks based on user input */
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes the specified task at index taskNumber (0-indexed) in the ArrayList.
     *
     * @param taskNumber The task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
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
     * Adds the specified reo.Todo object to the ArrayList.
     *
     * @param t The task to be added.
     */
    public void addTodo(Todo t) {
        tasks.add(t);
    }

    /**
     * Adds the specified reo.Deadline object to the ArrayList.
     *
     * @param d The task to be added.
     */
    public void addDeadline(Deadline d) {
        tasks.add(d);
    }

    /**
     * Adds the specified reo.Event object to the ArrayList.
     *
     * @param e The task to be added.
     */
    public void addEvent(Event e) {
       tasks.add(e);
    }

    /**
     * Get the size of the stored ArrayList so far.
     *
     * @return The size of the current ArrayList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Get the String representation of the task
     * at the specified index (0-indexed) of the ArrayList.
     *
     * @param taskNumber Index of the task of interest.
     * @return The String representation at index i.
     */
    public String getStringAt(int taskNumber) {
        return tasks.get(taskNumber).toString();
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Create the String representation of the stored ArrayList so far.
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
