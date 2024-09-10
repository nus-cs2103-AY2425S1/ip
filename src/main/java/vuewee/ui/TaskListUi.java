package vuewee.ui;

import vuewee.TasksStorage;
import vuewee.parser.IllegalCommandException;
import vuewee.task.Task;
import vuewee.task.TaskList;
import vuewee.task.TaskLocalDate;

/**
 * The TaskListUI interface represents the user interface for Vuewee. It
 * contains methods to add, delete, display, and mark tasks as done or not done.
 */
public abstract class TaskListUi {
    protected TaskList taskList;
    protected TasksStorage storage;

    /**
     * Creates a new TaskListUI object with an empty task list.
     */
    public TaskListUi() {
        this.taskList = new TaskList();
        this.storage = TasksStorage.getInstance();
    }

    /**
     * Creates a new TaskListUI object with the specified task list. Used for
     * reading and writing tasks to a file and for testing purposes.
     *
     * @param taskList Existing task list to be used
     */
    public TaskListUi(TaskList taskList) {
        this.taskList = taskList;
        this.storage = TasksStorage.getInstance();
    }

    /**
     * Runs the user interface.
     */
    public abstract void run();

    /**
     * Closes the user interface and any resources.
     */
    public abstract void close();

    /**
     * Simple helper method to determine if the task count is 1 or more
     */
    protected String taskWord() {
        return this.taskList.size() == 1 ? "task" : "tasks";
    }

    /**
     * Adds a task to the list.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskNumber The index of the task to be deleted (1-based)
     */
    public Task deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        taskNumber--; // Adjust task number to match array index

        if (taskNumber >= this.taskList.size() || taskNumber < 0) {
            throw new IndexOutOfBoundsException("Invalid task number. There are " + this.taskList.size() + " " + this
                    .taskWord() + " in your list.");
        }

        Task removedTask = this.taskList.get(taskNumber);
        this.taskList.remove(taskNumber);
        return removedTask;
    }

    /**
     * Display all tasks in the list
     *
     * @throws IllegalCommandException
     */
    public void displayTasks() throws IllegalCommandException {
        displayTasks(this.taskList);
    }

    /**
     * Display all tasks in the list that match the keyword. Search is done by
     * another method that returns a new TaskList with matching tasks.
     *
     * @param tasks TaskList to search for matching tasks
     * @throws IllegalCommandException
     */
    public abstract void displayTasks(TaskList tasks);

    /**
     * Display all tasks in the list that are scheduled for the specified date.
     *
     * @param date
     */
    public abstract void displaySchedule(TaskLocalDate date);

    /**
     * Marks a task as done or not done.
     *
     * @param taskNumber
     * @param isDone
     */
    public void markTask(int taskNumber, boolean isDone) throws IllegalCommandException {
        taskNumber--; // Adjust task number to match array index

        boolean isSuccessful = this.taskList.markTask(taskNumber, isDone);
        if (!isSuccessful) {
            throw new IllegalCommandException(this.taskList.get(taskNumber), isDone);
        }
    }
}
