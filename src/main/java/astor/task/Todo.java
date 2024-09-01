package astor.task;

public class Todo extends Task {
    public Todo(String taskInfo) {
        super(taskInfo);
    }

    @Override
    public String toString() {
        String s = "[T] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo();
        return s;
    }

    @Override
    public String dataDescription() {
        int i = isDone() ? 1 : 0;
        return "T | " + i + " | " + this.getTaskInfo();
    }
}
