package ava.task;

import ava.files.FileManager;
import ava.task.tasks.Deadline;
import ava.task.tasks.Event;
import ava.task.tasks.Todo;

import java.util.List;

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
        // in the future return a view of the list
        // rather than the list to avoid errors
        return tasks;
    }


    public List<Task> getTasks(String s) {
        // in the future return a view of the list
        // rather than the list to avoid errors
        return tasks;
    }

    private void sync(){
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
    public void addTask(String task,String time){
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
    public void addTask(String task,String startTime,String endTime){
        addTask(new Event(task, startTime, endTime));
    }


    /**
     * Removes a task from the list with the given taskId.
     *
     * @param taskId id of task to be removed.
     */
    public void removeTask(int taskId) {
        if(taskId > 0 && taskId <= tasks.size()) {
            tasks.remove(taskId - 1);
        } else {
            throw new IllegalArgumentException("Trying to delete nonexistent task");
        }

        sync();
    }
}
