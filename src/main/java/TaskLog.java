public class TaskLog {
    private Task[] log;
    private int numTasks;

    TaskLog(Task[] newLog) {
        this.log = newLog;
        this.numTasks = 0;
    }

    TaskLog() {
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

    private Task getTask(int pos) {
        if (pos < 0 || pos > (this.numTasks - 1)) {
            return new Task();
        }
        return this.log[pos];
    }

    public Task lastTask() {
        return this.getTask(this.numTasks - 1);
    }

    protected Task[] getLog() {
        Task[] truncLog = new Task[this.numTasks];
        for (int i = 0; i < this.numTasks; i++) {
            truncLog[i] = this.getTask(i);
        }
        return truncLog;
    }
}
