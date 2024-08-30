package yapmeister.task;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String exportString() {
        String completedString;
        if (this.isCompleted()) {
            completedString = "1";
        } else {
            completedString = "0";
        }
        return String.format("T|%s|%s", completedString, this.getTaskName());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
