package pikappi;

import java.util.ArrayList;
import java.util.Arrays;

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
    public String addTask(Task task) throws PikappiException {
        if (task == null) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        tasks.add(task);
        return ui.showAddedTask(task, tasks.size());
    }

    /**
     * Deletes a task from the list of tasks.
     *
     * @param taskNum Index of the task to be deleted
     * @throws PikappiException If the task does not exist
     */
    public String deleteTask(int taskNum) throws PikappiException {
        if (taskNum > tasks.size() || taskNum < 1) {
            throw new PikappiException("Pi-ka..?? Task does not exist..");
        }
        Task task = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        return ui.showDeletedTask(task, tasks.size());
    }

    /** Lists all tasks in the list of tasks. */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return ui.showNoTasks();
        }
        return ui.showAllTasks(this.tasks);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber Index of the task to be marked as done
     */
    public String markTask(int taskNumber) throws PikappiException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            return ui.showMarkedTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new PikappiException("Pi-ka..?? Task does not exist..");
        }
    }

    /**
     * Unmarks a task as done.
     *
     * @param taskNumber Index of the task to be unmarked as not done
     */
    public String unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.unmarkAsDone();
        return ui.showUnmarkedTask(task);
    }

    /**
     * Finds tasks that contain a keyword.
     *
     * @param keywords Keyword to search for in tasks
     * @return TaskList object that contains tasks that match the keyword
     * @throws PikappiException If no tasks match the keyword
     */
    public TaskList findTask(String... keywords) {
        TaskList matches = new TaskList();
        Arrays.stream(keywords)
                .forEach(keyword -> tasks.stream()
                        .filter(task -> task.getDescription().contains(keyword))
                        .forEach(task -> matches.getTasks().add(task)));
        return matches;
    }
}
