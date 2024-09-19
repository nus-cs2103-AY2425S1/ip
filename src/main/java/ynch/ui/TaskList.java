package ynch.ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks, allowing for management of todo items.
 */
class TaskList {
    ArrayList<Task> todoList;

    /**
     * Constructs an empty TaskList.
     */
    TaskList() {
        this.todoList = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param todoList an ArrayList of tasks to initialize the TaskList
     */
    TaskList(ArrayList<Task> todoList) {
        this.todoList = todoList;
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the size of the task list
     */
    int getSize() {
        return this.todoList.size();
    }

    boolean isEmpty() {
        return this.todoList.size() == 0;
    }

    Task add(Task task) {
        this.todoList.add(task);
        return task;
    }
    
    /**
     * Adds a new Todo task to the TaskList.
     *
     * @param task the description of the todo task
     * @return the newly created Todo task
     */
    Task add(String task) {
        Todo newTask = new Todo(task);
        this.todoList.add(newTask);
        return newTask;
    }

    /**
     * Adds a new Deadline task to the TaskList.
     *
     * @param task     the description of the deadline task
     * @param deadline the deadline date of the task
     * @return the newly created Deadline task
     */
    Task add(String task, String deadline) {
        Deadline newTask = new Deadline(task, deadline);
        this.todoList.add(newTask);
        return newTask;
    }

    /**
     * Adds a new Event task to the TaskList.
     *
     * @param task the description of the event task
     * @param from the start date of the event
     * @param to   the end date of the event
     * @return the newly created Event task
     */
    Task add(String task, String from, String to) {
        Event newTask = new Event(task, from, to);
        this.todoList.add(newTask);
        return newTask;
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param i the index of the task to delete (1-based)
     * @return the removed Task object
     */
    Task delete(int i) {
        int index = i - 1;
        Task removedTask = this.todoList.get(index);
        this.todoList.remove(index);
        return removedTask;
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param i the index of the task to mark (1-based)
     * @return the marked Task object
     */
    Task mark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.mark();
        this.todoList.set(index, taskToMark);
        return taskToMark;
    }

    /**
     * Unmarks a task as not done at the specified index.
     *
     * @param i the index of the task to unmark (1-based)
     * @return the unmarked Task object
     */
    Task unmark(int i) {
        int index = i - 1;
        Task taskToMark = this.todoList.get(index);
        taskToMark.unmark();
        this.todoList.set(index, taskToMark);
        return taskToMark;
    }

    
    TaskList find(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task t : this.todoList) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    TaskList getTasksToRemind() {
        TaskList remindTasks = new TaskList();
        for (Task t : this.todoList) {
            if (t.needsReminder()) {
                remindTasks.add(t);
            }
        }
        return remindTasks;
    }
    
    /**
     * Returns a string representation of all tasks in the TaskList.
     *
     * @return a formatted string listing all tasks
     */
    String list() {
        String list = "";
        int index = 0;
        for (int i = 0; i < this.todoList.size(); i++) {
            index += 1;
            list += index + ". " + this.todoList.get(i) + "\n";
        }
        return list;
    }

    /**
     * Returns a string representation of the TaskList suitable for saving to a file.
     *
     * @return a string representing all tasks in a save-friendly format
     */
    String toSaveAsString() {
        String output = "";
        for (Task t : this.todoList) {
            output = output + t.toSaveAsString() + "\n";
        }
        return output;
    }

    /**
     * Retrieves the list of tasks from the to-do list.
     *
     * @return An ArrayList containing Task objects that represent the current tasks
     *         in the to-do list. If no tasks are present, an empty list is returned.
     */
    ArrayList<Task> getTasks() {
        return this.todoList;
    }
}
