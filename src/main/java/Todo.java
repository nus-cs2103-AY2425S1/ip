public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        String status = this.isDone ? "1" : "0";
        return String.format("TODO | %s | %s\n", status, this.description);
    }

}
