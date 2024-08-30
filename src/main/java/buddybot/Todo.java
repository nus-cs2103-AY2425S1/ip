package buddybot;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public String toFile() {
        return "T" + super.toFile();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
