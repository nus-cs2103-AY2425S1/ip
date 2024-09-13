package Mario.Tasks;

public class Todo extends Task {
    String type;
    public Todo(String name) {
        super(name);
        this.type = "T";
    }

    public Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    @Override
    public String getName() {
        String status;
        if (isCompleted) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return "[T]" + status + " " + name;
    }

    @Override
    public String toFileFormat() {
        int status = this.isCompleted ? 1 : 0;
        return "T | " + status + " | " + this.name;
    }
}
