public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        String task = "T";

        return task + " | " + (this.isDone ? "1" : "0") + " | "
                + this.description;
    }

}
