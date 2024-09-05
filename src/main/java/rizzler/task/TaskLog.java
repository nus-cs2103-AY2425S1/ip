package rizzler.task;

import rizzler.ui.RizzlerException;

/**
 * Log to keep track of all the tasks on the user's list.
 */
public class TaskLog {
    private Task[] log;
    private int numTasks;

    private TaskLog(Task[] newLog) {
        this.log = newLog;
        this.numTasks = 0;
    }

    /**
     * Constructor for creation of a new empty TaskLog.
     */
    public TaskLog() {
        this(new Task[100]);
    }

    /**
     * Adds a given task to the taskLog. Doubles the size of the taskLog if necessary.
     * @param newTask New task to be added to the log.
     */
    public void addTask(Task newTask) {
        this.log[this.numTasks] = newTask;
        this.numTasks++;
        if (this.numTasks == this.log.length) {
            this.doubleLogSize();
        }
    }

    private void doubleLogSize() {
        int newLogSize = 2 * this.log.length;
        Task[] newLog = new Task[newLogSize];
        System.arraycopy(this.log, 0, newLog, 0, this.log.length);
        this.log = newLog;
    }

    protected Task getTask(int pos) throws RizzlerException {
        if (pos >= this.numTasks) {
            throw new RizzlerException("there ain't no task " + (pos + 1) + " darlin'");
        } else if (pos < 0) {
            throw new RizzlerException("pumpkin, why you tryna give me problems?");
        }
        return this.log[pos];
    }

    public Task lastTask() throws RizzlerException {
        return this.getTask(this.numTasks - 1);
    }

    /**
     * Outputs the log of tasks, truncated by length to the number of tasks present in the log.
     * @return Array of tasks on the user's list.
     */
    public Task[] getLog() {
        Task[] truncLog = new Task[this.numTasks];
        for (int i = 0; i < this.numTasks; i++) {
            truncLog[i] = this.getTask(i);
        }
        return truncLog;
    }

    /**
     * Marks a task as done.
     * @param taskNum Number of the task to be marked as done, as shown in <code>list</code>.
     * @return The task that has been completed, for any other operations.
     * @throws RizzlerException If the task number given does not exist in the log.
     */
    public Task doTask(int taskNum) throws RizzlerException {
        Task doneTask = getTask(taskNum - 1);
        doneTask.done();
        return doneTask;
    }

    /**
     * Marks a task as undone.
     * @param taskNum Number of the task to be marked as undone, as shown in <code>list</code>.
     * @return The task that has to be marked as incomplete, for any other operations.
     * @throws RizzlerException If the task number given does not exist in the log.
     */
    public Task undoTask(int taskNum) throws RizzlerException {
        Task undoneTask = getTask(taskNum - 1);
        undoneTask.undone();
        return undoneTask;
    }

    /**
     * Returns the number of tasks within the taskLog.
     * @return Number of tasks in taskLog.
     */
    public int getNumTasks() {
        return numTasks;
    }

    /**
     * Deletes a task from the taskLog, permanently removing it.
     * @param taskNum Number of the task to be deleted from the log. as shown in <code>list</code>.
     * @return The deleted task for any operations before it gets lost permanently.
     * @throws RizzlerException If the task number given does not exist in the taskLog.
     */
    public Task deleteTask(int taskNum) throws RizzlerException {
        Task[] newTaskLog = new Task[log.length];
        Task taskToDelete = getTask(taskNum - 1);
        System.arraycopy(log, 0, newTaskLog, 0, taskNum - 1);
        System.arraycopy(log, taskNum, newTaskLog, taskNum - 1, numTasks - taskNum + 1);
        log = newTaskLog;
        numTasks--;
        return taskToDelete;
    }
}
