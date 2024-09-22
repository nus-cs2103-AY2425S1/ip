package botimusprime.tasks;

public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String saveString() {
        return String.format("T | %s | %s", description, isDone);
    }

    @Override
    public boolean isEvent() {
        return false;
    }

    @Override
    public boolean isToDo() {
        return true;
    }

    @Override
    public boolean isDeadline() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}