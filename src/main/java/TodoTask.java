public class TodoTask extends Task{
    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + super.description;
    }

}
