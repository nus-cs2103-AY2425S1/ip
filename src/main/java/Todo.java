public class Todo extends Task {
    private String type = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskInfo() {
        return(super.description);
    }

    @Override
    public String getTaskType() {
        return this.type;
    }
}
