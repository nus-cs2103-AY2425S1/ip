package reo;

import java.util.ArrayList;

/** Manages the list of tasks stored */
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

    /**
     * Get the Task object
     * at the specified index (0-indexed) of the ArrayList.
     *
     * @param taskNumber Index of the task of interest.
     * @return The Task object at index i.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Filter the current task list.
     *
     * @param s The keyword to search for.
     * @return A new ArrayList, which only contains tasks whose names
     * contain the keyword s.
     */
    public ArrayList<Task> filter(String s) {
        ArrayList<Task> copy = new ArrayList<>();
        copy.addAll(tasks);
        copy.removeIf(t -> !t.nameDoesContain(s));
        return copy;
    }

    /**
     * Create the String representation of the stored ArrayList so far.
     *
     * @return The String representation of the stored ArrayList so far.
     */
    @Override
    public String toString() {
        String res = "";
//        if (tasks.size() == 0) {
//            return "The list is empty.\n";
//        }
        for (int i = 0; i < tasks.size(); i++) {
            int listIndex = i + 1;
            res += listIndex + ". " + tasks.get(i).toString() + "\n";
        }

        return res;
    }
}
