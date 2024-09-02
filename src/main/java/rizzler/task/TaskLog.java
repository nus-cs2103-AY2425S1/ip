package rizzler.task;

import rizzler.ui.RizzlerException;

public class TaskLog {
    private Task[] log;
    private int numTasks;

    public TaskLog(Task[] newLog) {
        this.log = newLog;
        this.numTasks = 0;
    }

    public TaskLog() {
        this(new Task[100]);
    }

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

    public Task[] getLog() {
        Task[] truncLog = new Task[this.numTasks];
        for (int i = 0; i < this.numTasks; i++) {
            truncLog[i] = this.getTask(i);
        }
        return truncLog;
    }

    public Task doTask(int taskNum) throws RizzlerException {
        Task doneTask = getTask(taskNum - 1);
        doneTask.done();
        return doneTask;
    }

    public Task undoTask(int taskNum) throws RizzlerException {
        Task undoneTask = getTask(taskNum - 1);
        undoneTask.undone();
        return undoneTask;
    }

    public int getNumTasks() {
        return numTasks;
    }

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
