package pikappi;

import java.util.ArrayList;
import java.util.Arrays;

import pikappi.exception.PikappiException;
import pikappi.task.DeadlineTask;
import pikappi.task.EventTask;
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
        assert tasks != null : "Tasks should not be null";
        return tasks;
    }

    /**
     * Loads and adds a task to the list of tasks without printing statement.
     * Used for loading tasks from Storage.
     *
     * @param task Task to be added
     */
    public void load(Task task) {
        assert task != null : "Task should not be null";
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
        assert taskNum > 0 : "Task number should be greater than 0";
        if (taskNum > tasks.size()) {
            throw new PikappiException("Pi-ka..?? Task does not exist..");
        }
        Task task = tasks.get(taskNum - 1);
        assert task != null : "Task should not be null";
        tasks.remove(taskNum - 1);
        return ui.showDeletedTask(task, tasks.size());
    }

    /** Lists all tasks in the list of tasks. */
    public String listTasks() {
        assert tasks != null : "Tasks should not be null";
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
        assert taskNumber > 0 : "Task number should be greater than 0";
        try {
            Task task = tasks.get(taskNumber - 1);
            assert task != null : "Task should not be null";
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
        assert taskNumber > 0 : "Task number should be greater than 0";
        Task task = tasks.get(taskNumber - 1);
        assert task != null : "Task should not be null";
        task.unmarkAsDone();
        return ui.showUnmarkedTask(task);
    }

    /**
     * Finds tasks that contain a keyword.
     *
     * @param keywords Keyword to search for in tasks
     * @return TaskList object that contains tasks that match the keyword
     */
    public TaskList findTask(String... keywords) {
        TaskList matches = new TaskList();
        Arrays.stream(keywords)
                .forEach(keyword -> tasks.stream()
                        .filter(task -> task.getDescription().contains(keyword))
                        .forEach(task -> matches.getTasks().add(task)));
        return matches;
    }

    /**
     * Sorts tasks by description, task type or status.
     *
     * @param sortBy The attribute to sort tasks by
     * @throws PikappiException If the sort option is invalid
     */
    public void sortTasks(String sortBy) throws PikappiException {
        switch (sortBy) {
        case "description":
            tasks.sort((task1, task2) -> task1.getDescription().compareTo(task2.getDescription()));
            break;
        case "tasktype":
            tasks.sort((task1, task2) -> task1.getTaskType().compareTo(task2.getTaskType()));
            break;
        case "status":
            tasks.sort((task1, task2) -> Boolean.compare(task1.isDone(), task2.isDone()));
            break;
        case "by":
            tasks.sort((task1, task2) -> {
                if (task1 instanceof DeadlineTask deadlineTask1 && task2 instanceof DeadlineTask deadlineTask2) {
                    return deadlineTask1.getBy().compareTo(deadlineTask2.getBy());
                } else if (task1.getTaskType().equals("D")) {
                    return -1;
                } else if (task2.getTaskType().equals("D")) {
                    return 1;
                } else {
                    return 0;
                }
            });
            break;
        case "from":
            tasks.sort((task1, task2) -> {
                if (task1 instanceof EventTask eventTask1 && task2 instanceof EventTask eventTask2) {
                    return eventTask1.getFrom().compareTo(eventTask2.getFrom());
                } else if (task1.getTaskType().equals("E")) {
                    return -1;
                } else if (task2.getTaskType().equals("E")) {
                    return 1;
                } else {
                    return 0;
                }
            });
            break;
        default:
            throw new PikappiException("Pi-ka..?? Invalid sort option..");
        }
    }
}
