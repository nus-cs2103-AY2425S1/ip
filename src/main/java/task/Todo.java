package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.type = "[T]";
        super.outputTaskCount();
    }

    @Override
    public String toString() {
            return this.type + "[" + this.status + "] - " + this.description;
    }
}
