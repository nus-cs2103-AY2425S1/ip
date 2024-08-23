public class TaskList {
    private static Task[] taskList;
    private static int length;

    private Ui ui;

    /**
     * Constructs a TaskList with an initial capacity of 100 tasks
     * The TaskList is initialized with an empty array of Tasks, a length of 0, and a new Ui instance
     */
    public TaskList() {
        taskList = new Task[100];
        length = 0;
        ui = new Ui();
    }

    /**
     * Returns the list of tasks represented by the TaskList
     *
     * @return An array of Task objects representing the current list of tasks
     */
    public static Task[] getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of tasks currently in the TaskList
     *
     * @return The number of tasks in the TaskList
     */
    public static int getTaskListLength() {
        return length;
    }

    /**
     * Adds a new task to the TaskList
     *
     * @param task The Task object to be added to the TaskList
     */
    public String addTask(Task task) {
        taskList[length] = task;
        length++;
        return ui.displayAddedTask(task);
    }

    /**
     * Marks a task as completed based on its index in the TaskList
     *
     * @param taskNum The index of the task (1-based) to be marked as completed
     * @return A string message indicating the result of the marking operation
     */
    public String markTask(int taskNum) {
        Task t = taskList[taskNum - 1];
        if (t.isDone()){
            return ui.displayAlreadyMarkedTask();
        }
        t.setDone(true);
        return ui.displayMarkedTask(t);
    }

    /**
     * Unmarks a task as not completed based on its index in the TaskList
     *
     * @param taskNum The index of the task (1-based) to be unmarked
     * @return A string message indicating the result of the unmarking operation
     */
    public String unmarkTask(int taskNum) {
        Task t = taskList[taskNum - 1];
        if (!t.isDone()){
            return ui.displayAlreadyUnmarkedTask();
        }
        t.setDone(false);
        return ui.displayUnmarkedTask(t);
    }
}
