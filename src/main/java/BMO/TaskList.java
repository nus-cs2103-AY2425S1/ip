package bmo;
import bmo.task.Deadline;
import bmo.task.Event;
import bmo.task.Task;
import bmo.task.ToDo;

import java.io.IOException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Adds a task to the task list.
     * 
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the list of tasks.
     * 
     * @return ArrayList of all tasks.
     */ 
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the task at the specified index.
     * 
     * @param index of the task to be retrieved.
     * @return Task object at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Deletes a task from the list
     * 
     * @param index of the task to be deleted.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Marks a task as completed
     * 
     * @param index of the task to be marked as completed.
     */
    public void markTask(int index) {
        this.getTask(index).mark();
    }

    /**
     * Unmarks a task as incomplete.
     * 
     * @param index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        this.getTask(index).unmark();
    }

    /**
     * Adds a todo task to the list.
     * 
     * @param description of the todo task.
     */
    public void addTodo(String description) {
        Task todo = new ToDo(description);
        this.addTask(todo);
    }

    /**
     * Adds a deadline task to the list.
     * 
     * @param description of the deadline task.
     * @param by the deadline of the task. Format: "dd/MM/yyyy"
     */
    public void addDeadline(String description, String by) {
        Task deadline = new Deadline(description, by);
        this.addTask(deadline);
    }

    /**
     * Adds an event task to the list.
     * 
     * @param description of the event task.
     * @param from the start date of the event. Format: "dd/MM/yyyy"
     * @param to the end date of the event. Format: "dd/MM/yyyy"
     */
    public void addEvent(String description, String from, String to) throws IOException {
        Task event = new Event(description, from, to);
        this.addTask(event);
    }
}