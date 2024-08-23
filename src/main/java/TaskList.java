public class TaskList {
    private static Task[] taskList;
    private static int length;

    /**
     * Constructor for TaskList class, taskList initialized with array of Tasks
     */
    public TaskList() {
        taskList = new Task[100];
        length = 0;
    }

    /**
     *
     * Returns the list of Tasks
     */
    public static Task[] getTaskList() {
        return taskList;
    }

    /**
     *
     * Returns the length of the list of Tasks
     */
    public static int getTaskListLength() {
        return length;
    }
}
