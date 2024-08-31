package pikappi;

import java.util.ArrayList;

import pikappi.exception.PikappiException;
import pikappi.task.Task;

/** Represents a list of tasks. */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui = new Ui();

    /** Returns a TaskList object that contains no tasks. */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns a TaskList object that contains tasks.
     *
     * @param tasks List of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a TaskList object that contains tasks.
     *
     * @return List of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Loads and adds a task to the list of tasks without printing statement.
     * Used for loading tasks from Storage.
     *
     * @param task Task to be added
     */
    public void load(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a task to the list of tasks.
     * Used for adding tasks from user input.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) throws PikappiException {
        if (task == null) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        tasks.add(task);
        ui.showAddedTask(task, tasks.size());
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param taskNum Index of the task to be deleted
     * @throws PikappiException If the task does not exist
     */
    public void deleteTask(int taskNum) throws PikappiException {
        if (taskNum > tasks.size() || taskNum < 1) {
            throw new PikappiException("Pi-ka..?? Task does not exist..");
        }
        Task task = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        ui.showDeletedTask(task, tasks.size());
    }

    /** Lists all tasks in the list of tasks. */
    public void listTasks() {
        if (tasks.isEmpty()) {
            ui.showNoTasks();
            return;
        }
        ui.showAllTasks(this.tasks);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber Index of the task to be marked as done
     */
    public void markTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        ui.showMarkedTask(task);
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskNumber Index of the task to be unmarked as not done
     */
    public void unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.unmarkAsDone();
        ui.showUnmarkedTask(task);
    }

    /**
     * Finds tasks that contain a keyword.
     *
     * @param keyword Keyword to search for in tasks
     * @return TaskList object that contains tasks that match the keyword
     * @throws PikappiException If no tasks match the keyword
     */
    public TaskList findTask(String keyword) throws PikappiException {
        TaskList matches = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.getTasks().add(task);
            }
        }
        return matches;
    }
}
