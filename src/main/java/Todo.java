public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public  String toFileRecord() {
        Integer status = isDone ? 1 : 0;
        return "T" + " | " + status + " | " + this.description;
    }
    @Override
    public String getStatus() {
        return "[T]" + "[" + super.getStatusIcon() + "]" + " " + super.toString();
    }
}
