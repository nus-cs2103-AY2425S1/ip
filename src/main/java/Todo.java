public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFormatted() {
        return "T," + this.isDone() + "," + this.description + "\n";
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
