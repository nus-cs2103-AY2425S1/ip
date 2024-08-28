public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
    @Override
    public String toFileString() {
        return "T | " + getStatusIcon() + " | " + getDescription();
    }

    
}
