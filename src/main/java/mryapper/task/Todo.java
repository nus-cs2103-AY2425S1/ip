package mryapper.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDataString() {
        return String.format("T ||| %d ||| %s\n",
                this.getStatus(), this.getDescription());
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
