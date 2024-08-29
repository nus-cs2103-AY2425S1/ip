package elster.tasks;

public class TaskStub extends Task{
    public TaskStub(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "";
    }

    public String getDescription() {
        return description;
    }
}
