public class TaskEvent extends Task {
    public TaskEvent(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
