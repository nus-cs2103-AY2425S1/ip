package storage;

import commands.Command;

import tasks.Task;
import tasks.Todo;
import tasks.Event;
import tasks.Deadline;

import exceptions.InvalidTaskException;

import java.util.ArrayList;

/**
 * Stores the list of tasks.
 * Responsible for manipulating tasks, such as adding, marking, unmarking and deleting.
 * Responsible for printing out all the tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> TASKS;

    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    /**
     * Adds a task to TASKS.
     *
     * @param type type of task to be added.
     * @param response arguments that the task has.
     * @return the task that has been added to TASKS.
     */
    public Task addTask(Command type, String[] response) {
        Task task = new Task(response[0]);
        switch (type) {
        case TODO:
            task = new Todo(response[1]);
            break;
        case DEADLINE:
            // response[0] = description, response[1] = deadline
            task = new Deadline(response[0], response[1].strip());
            break;
        case EVENT:
            String[] times = response[1].split("/to ");
            task = new Event(response[0], times[0].strip(), times[1].strip());
            break;
        default:
            break;
        }
        TASKS.add(task);
        return task;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        TASKS.add(task);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber the (index + 1) of the task.
     * @return the task that was marked.
     * @throws InvalidTaskException if taskNumber > TASKS.size() or taskNumber == 0.
     */
    public Task markTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > TASKS.size() || taskNumber == 0) {
            throw new InvalidTaskException("", taskNumber);
        }
        Task markTask = TASKS.get(taskNumber - 1);
        markTask.markAsDone();
        return markTask;
    }

    /**
     * Unmarks a task.
     *
     * @param taskNumber the (index + 1) of the task.
     * @return the task that was unmarked.
     * @throws InvalidTaskException if taskNumber > TASKS.size() or taskNumber == 0.
     */
    public Task unmarkTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > TASKS.size() || taskNumber == 0) {
            throw new InvalidTaskException("", taskNumber);
        }
        Task unmarkTask = TASKS.get(taskNumber - 1);
        unmarkTask.markAsUndone();
        return unmarkTask;
    }

    /**
     * Deletes the specified task.
     *
     * @param taskNumber the (index + 1) of the task.
     * @return the task that was deleted.
     * @throws InvalidTaskException if taskNumber > TASKS.size() or taskNumber == 0.
     */
    public Task deleteTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > TASKS.size() || taskNumber == 0) {
            throw new InvalidTaskException("", taskNumber);
        }
        return TASKS.remove(taskNumber - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return number of tasks in the list.
     */
    public int getSize() {
        return this.TASKS.size();
    }

    /**
     * Returns the string representation of all the tasks in the list.
     *
     * @return string of tasks.
     */
    public String listTasks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < TASKS.size(); i++) {
            String taskInfo = String.format("%d. %s", i + 1, TASKS.get(i).toString());
            s.append(taskInfo).append("\n");
        }
        return s.toString();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index index in array.
     * @return task at the given index.
     */
    public Task getTask(int index) {
        return TASKS.get(index);
    }

    /**
     * Finds tasks whose description matches the keyword.
     * @param keyword the keyword to check.
     * @return String of tasks which contains the keyword.
     */
    public String findTasks(String keyword) {
        TaskList filteredTasks = new TaskList();
        for (Task task : TASKS) {
            String[] keywords = task.getDescription().split(" ");
            for (String word : keywords) {
                if (word.equals(keyword)) {
                    filteredTasks.addTask(task);
                    break;
                }
            }
        }
        return filteredTasks.listTasks();
    }
}
