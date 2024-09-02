public class Todo extends Task {
    private String type = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, int status) {
        super(description, status);
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
