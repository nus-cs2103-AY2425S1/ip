package ava.task;

import java.util.List;

import ava.files.FileManager;
import ava.task.tasks.Deadline;
import ava.task.tasks.Event;
import ava.task.tasks.Todo;



/**
 *  Creates a TaskManager to manage tasks for AVA.
 */
public class TaskManager {

    private final List<Task> tasks;
    private final FileManager fileManager;

    /**
     * Creates a new TaskManager.
     */
    public TaskManager() {
        this.fileManager = new FileManager();
        this.tasks = fileManager.getTasks();
    }


    /**
     * Retrieves the tasks in the form of a list.
     *
     * @return the tasks in the form of a list.
     */
    public List<Task> getTasks() {
        return tasks;
    }


    /**
     * Retrieves the tasks containing the
     * search keyword in the form of a list.
     *
     * @param s the string to be searched.
     * @return the tasks in the form of a list.
     */
    public List<Task> getTasks(String s) {
        List<Task> filtered = tasks.stream().filter(task -> task.getTitle().contains(s)).<Task>toList();
        // formats the list of tasks properly
        return new TaskList(filtered);
    }

    private void sync() {
        fileManager.writeTasks(tasks);
    }

    /**
     * Adds a Task to the List
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);

        sync();
    }

    /**
     * Adds a To-do to the list with the given title.
     *
     * @param task description of to-do.
     */
    public void addTask(String task) {
        addTask(new Todo(task));
    }

    /**
     * Adds a Deadline to the list with the given title and time.
     *
     * @param task description of deadline.
     * @param time time for deadline.
     */
    public void addTask(String task, String time) {
        addTask(new Deadline(task, time));
    }

    /**
     * Adds an Event to the list with the given title,
     * startTime and endTime.
     *
     * @param task description of Event.
     * @param startTime start time of event.
     * @param endTime end time of event.
     */
    public void addTask(String task, String startTime, String endTime) {
        addTask(new Event(task, startTime, endTime));
    }


    /**
     * Removes a task from the list with the given taskId.
     *
     * @param taskId id of task to be removed.
     */
    public Task removeTask(int taskId) {
        Task item;
        if (taskId > 0 && taskId <= tasks.size()) {
            item = tasks.get(taskId - 1);
            tasks.remove(taskId - 1);
        } else {
            System.out.println("Sorry, I could not find the task you wanted me to remove 😢.");
            throw new IllegalArgumentException("Trying to delete nonexistent task");
        }

        sync();
        return item;
    }
}
