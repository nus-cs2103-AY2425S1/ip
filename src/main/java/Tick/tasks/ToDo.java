package Tick.tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toFileString() {
        return String.format("T | %s | %s", super.getStatus() ? "1" : "0", super.getDescription());
    }

    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
