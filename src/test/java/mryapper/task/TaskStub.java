package mryapper.task;

public class TaskStub extends Task {

    public TaskStub() {
        super("stub");
    }
    @Override
    public String getDataString() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("[%s] stub", super.getStatusIcon());
    }
}
