package luke.task;

public class Todo extends Task {
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String taskDescription() {
        return "[T]" + showMark() + " " + this.name;
    }

    @Override
    public String taskInSaveData() {
        return (isDone ? "X" : "-")
                + " | todo | "
                + name + "\n";
    }
}
