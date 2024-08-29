package ginger.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Encapsulates a task handler, which contains the list of tasks and local database of task. The task handler will
 * manage addition and removal of tasks, as well as marking and unmarking of them.
 */
public class TaskHandler {
    private final List<Task> taskList;
    private final TaskStorage taskStorage;

    /**
     * Creates a TaskHandler to be used to manage tasks.
     */
    public TaskHandler() {
        this.taskStorage = new TaskStorage("tasks.txt");
        this.taskList = this.taskStorage.readTasks();
    }

    /**
     * Returns the number of tasks that are currently present in the task list.
     */
    public int taskCount() {
        return this.taskList.size();
    }

    /**
     * Returns the Task at the given index.
     * @param index The index of the task to be retrieved.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the task list at the given index.
     * @param index The index of the task to be removed.
     * @return The removed task from the task list.
     */
    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    public String tasklistToString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(String.format("%d. %s", i + 1, taskList.get(i)));
            if (i != taskList.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public String tasklistToString(List<Task> taskList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(String.format("%d. %s", i + 1, taskList.get(i)));
            if (i != taskList.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Changes a task status to mark or unmarked
     * @param index The index of the task that is being referenced.
     * @param isCompleted A boolean to indicate whether to mark or unmark. True to mark, false to unmark.
     */
    public void changeTaskStatus(int index, boolean isCompleted) {
        Task t = taskList.get(index);
        t.setCompleted(isCompleted);
    }

    /**
     * Adds a new To Do to the task list.
     * @param title The title of the To Do.
     * @return The newly created To Do.
     */
    public Task addToDo(String title) {
        ToDo newToDo = new ToDo(title);
        this.addTask(newToDo);
        return newToDo;
    }

    /**
     * Adds a new Deadline to the task list.
     * @param title The title of the Deadline.
     * @param deadline The deadline of the task.
     * @return The newly created Deadline.
     */
    public Task addDeadline(String title, LocalDateTime deadline) {
        Deadline newDeadline = new Deadline(title, deadline);
        this.addTask(newDeadline);
        return newDeadline;
    }

    /**
     * Adds a new Event to the task list.
     * @param title The title of the Event.
     * @param start The start time of the Event.
     * @param end The end time of the Event.
     * @return The newly created Event.
     */
    public Task addEvent(String title, LocalDateTime start, LocalDateTime end) {
        Event newEvent = new Event(title, start, end);
        this.addTask(newEvent);
        return newEvent;
    }

    /**
     * Saves the tasks to the local database.
     */
    public void saveTasks() {
        this.taskStorage.saveTasks(this.taskList);
    }

    public List<Task> findTasks(String input) {
        return this.taskList.stream()
                .filter(task -> task.getTitle().contains(input))
                .collect(Collectors.toList());
    }
}
