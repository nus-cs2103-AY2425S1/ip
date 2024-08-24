public class Todo extends Task {

    protected String desc;

    public Todo(String description) {
        super(description);
        this.desc = description.split("todo ")[1];
    }

    public String toString() {
        return "[T]" + this.getStatusIcon() + this.desc;
    }
}
