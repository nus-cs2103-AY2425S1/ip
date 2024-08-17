public class Todo extends Task {
    private String type = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return this.type;
    }
}
