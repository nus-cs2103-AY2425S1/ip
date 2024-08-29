package yoda.tasks;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getData() {
        String isDone = this.isDone ? "1" : "0";
        return "T | " + isDone + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
