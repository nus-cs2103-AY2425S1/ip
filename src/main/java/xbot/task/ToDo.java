package xbot.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description, TaskType.T);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
