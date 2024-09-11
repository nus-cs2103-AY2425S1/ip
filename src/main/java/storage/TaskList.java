package storage;

import java.util.ArrayList;

import commands.Command;
import exceptions.InvalidTaskException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Stores the list of tasks.
 * Responsible for manipulating tasks, such as adding, marking, unmarking and deleting.
 * Responsible for printing out all the tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to TASKS.
     *
     * @param type type of task to be added.
     * @param params arguments that the task has.
     * @return the task that has been added to TASKS.
     */
    public Task addTask(Command type, String ... params) {
        Task task = new Task(params[0]);

        assert type == Command.TODO
                || type == Command.DEADLINE
                || type == Command.EVENT
                : "invalid task type to call function addTask";

        switch (type) {
        case TODO:
            task = new Todo(params[0]);
            break;
        case DEADLINE:
            task = new Deadline(params[0], params[1]);
            break;
        case EVENT:
            task = new Event(params[0], params[1], params[2]);
            break;
        default:
            break;
        }
        tasks.add(task);
        return task;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber the (index + 1) of the task.
     * @return the task that was marked.
     * @throws InvalidTaskException if taskNumber > TASKS.size() or taskNumber == 0.
     */
    public Task markTask(int taskNumber) throws InvalidTaskException {
        checkValidTaskIndex(taskNumber);
        Task markTask = tasks.get(taskNumber - 1);
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
        checkValidTaskIndex(taskNumber);
        Task unmarkTask = tasks.get(taskNumber - 1);
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
        checkValidTaskIndex(taskNumber);
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the string representation of all the tasks in the list.
     *
     * @return string of tasks.
     */
    public String listTasks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String taskInfo = String.format("%d. %s", i + 1, tasks.get(i).toString());
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
        return tasks.get(index);
    }

    /**
     * Finds tasks whose description matches the keyword.
     *
     * @param keyword the keyword to check.
     * @return String of tasks which contains the keyword.
     */
    public String findTasks(String keyword) {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks) {
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

    /**
     * Checks if the task index is within the size of the task list.
     *
     * @param taskNumber the index to check.
     * @throws InvalidTaskException when index is outside range of list.
     */
    private void checkValidTaskIndex(int taskNumber) throws InvalidTaskException {
        if (taskNumber > tasks.size() || taskNumber <= 0) {
            throw new InvalidTaskException("", taskNumber);
        }
    }
}
