public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "[T]";
    }

    @Override
    public String getDescription() {
        return this.getTaskType() + super.getDescription();
    }
}
