package rizz.tasks;

public class ToDo extends Task{
    public ToDo(String text, boolean isDone) {
        super(text, isDone);
    }
    @Override
    public String export() {
        return "T | " + (isDone ? "1" : "0") + " | " + text;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
