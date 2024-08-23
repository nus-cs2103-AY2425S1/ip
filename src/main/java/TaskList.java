public class TaskList {
    private static Task[] taskList;
    private static int length;

    private Ui ui;

    /**
     * Constructor for TaskList class, taskList initialized with array of Tasks
     */
    public TaskList() {
        taskList = new Task[100];
        length = 0;
        ui = new Ui();
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

    /**
     *
     * @param task adds a new Task to the TaskList
     */
    public void addTask(Task task) {
        taskList[length] = task;
        length++;
    }

    public String markTask(int taskNum) {
        Task t = taskList[taskNum - 1];
        if (t.isDone()){
            return ui.displayAlreadyMarkedTask();
        }
        t.setDone(true);
        return ui.displayMarkedTask(t);
    }

    public String unmarkTask(int taskNum) {
        Task t = taskList[taskNum - 1];
        if (!t.isDone()){
            return ui.displayAlreadyUnmarkedTask();
        }
        t.setDone(false);
        return ui.displayUnmarkedTask(t);
    }
}
