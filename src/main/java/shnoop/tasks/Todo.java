package shnoop.tasks;

public class Todo extends Task {
    public Todo (String description) {
        super(description.substring(5, description.length()));
    }

    public Todo (String description, boolean done) {
        super(description);
        if (done) {
            this.markTask();
        }
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toUString() {
        String s = super.toUString();
        s += "001"; // Unique identifier for Todo Tasktype
        s += super.description;
        return s;
    }
}
