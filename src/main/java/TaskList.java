public class TaskList {
    private static Task[] taskList;

    /**
     * Constructor for TaskList class, taskList initialized with array of Tasks
     */
    public TaskList() {
        taskList = new Task[100];
    }

    /**
     *
     * Returns the list of Tasks
     */
    public Task[] getTaskList() {
        return taskList;
    }
}
