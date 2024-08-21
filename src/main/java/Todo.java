public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + (this.getStatus() ? "X" : " ") + "] " + this.getDescription();
    }

}
