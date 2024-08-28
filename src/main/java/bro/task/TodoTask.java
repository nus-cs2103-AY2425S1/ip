package bro.task;

public class TodoTask extends Task {
    public TodoTask(String content) {
        super(content);
    }

    public TodoTask(String content, boolean isCompleted) {
        super(content, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
