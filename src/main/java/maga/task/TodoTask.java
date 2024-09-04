package maga.task;

public class TodoTask extends Task {

    public TodoTask(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    public String getTaskType() {
        return "[T]";
    }

    @Override
    public String toString() {
        int isDoneNum = 0;
        if (isDone) {
            isDoneNum = 1;
        }
        return "T | " + isDoneNum + " | " + description;
    }

    @Override
    public String printTask() {
        return this.getTaskType() + this.getStatusIcon() + this.getDescription();
    }
}