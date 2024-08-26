public class Todo extends Task {
    private String type;

    public Todo(String desc, String type, boolean isDone) {
        super(desc, isDone);
        this.type = type;
    }

    @Override
    public String taskToString() {
        return this.type + "," + super.getStatus() + "," + super.getDesc() + "\n";
    }

    @Override
    public String getType() {
        return this.type;
    }
}
