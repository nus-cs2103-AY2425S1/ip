package Papagu.Ui;

/**
 * The TaskList class represents list of tasks input by user.
 * As per requirements it can store up to 100 tasks.
 */
public class TaskList {
    private Task[] tasks;
    private int taskCount;

    /**
     * Constructs a new TaskList with an Array of 100 tasks.
     * Initializes the task count to zero.
     */
    public TaskList() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    /**
     * Adds a task to the TaskList.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        //Create new task object from user input
        this.tasks[this.taskCount] = task;
        this.taskCount++;
    }
    
    /**
     * Marks a task as done.
     * @param taskNumber
     */
    public void markTaskAsDone(int taskNumber) {
        this.tasks[taskNumber].markAsDone();
    }
    /**
     * Marks a task as not done.
     * @param taskNumber
     */
    public void markTaskAsNotDone(int taskNumber) {
        this.tasks[taskNumber].markAsNotDone();
    }
    /**
     * Returns the number of tasks in the TaskList.
     * @return The number of tasks in the TaskList.
     */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Deletes a task from the TaskList.
     * @param taskNumber The index of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        for (int i = taskNumber; i < this.taskCount - 1; i++) {
            this.tasks[i] = this.tasks[i + 1];
        }
        this.taskCount--;
        this.tasks[this.taskCount] = null;
    }

    public Task getTask(int taskNumber) {
        return this.tasks[taskNumber];
    }

    /**
     * Returns a string representation of the task list.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.taskCount; i++) {
            if (tasks[i] == null) {
                break;
            }
            result = result + (i + 1) + "." + tasks[i] + "\n";
        }
        return result;
    }

    /**
     * Finds tasks that match a given keyword
     * @param keyword
     * @return TaskList containing tasks that match the keyword
     */
    public TaskList findTasks(String keyword) {
        TaskList result = new TaskList();
        for (int i = 0; i < this.taskCount; i++) {
            if (tasks[i].isMatching(keyword)) {
                result.addTask(tasks[i]);
            }
        }
        return result;
    }
}