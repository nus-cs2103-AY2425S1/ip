package Task;

import java.time.format.DateTimeFormatter;
public abstract class Task {
    protected final static DateTimeFormatter toSelfFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm");
    protected final static DateTimeFormatter toUserFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    private final String task;
    public String type;
    private boolean completed;

    public Task(String t) {
        this.task = t;
        this.completed = false;
        TaskList.mainTaskList.addTask(this);
    }

    protected void done() {
        this.completed = true;
    }

    protected void undone() {
        this.completed = false;
    }

    public abstract String saveFileFormat();

    public boolean getCompleted() {
        return this.completed;
    }

    public String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        String done = (this.completed ? "[X]" : "[ ]");

        return this.type + " " + done + " " + this.task;
    }

    public boolean containsTerm(String term) {
        int b = this.task.indexOf(term);
        return (b != -1);
    }

    public String getDescription() {
        return this.task;
    }
}