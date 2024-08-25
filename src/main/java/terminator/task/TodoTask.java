package terminator.task;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String getDataRepresentation() {
        return super.getDataRepresentation() + this.description;
    }
}
