public class ToDoTask extends Task {
    public ToDoTask(String desc) {
        super(desc);
    }

    public ToDoTask(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
