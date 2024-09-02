public class DefaultTask extends Task {
    public DefaultTask(String description) {
        super(description);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEFAULT;
    }

    @Override
    public String toString() {
        return super.getDescription();
    }
}
