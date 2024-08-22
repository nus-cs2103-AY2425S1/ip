public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    public static ToDoTask of(String input) {
        return new ToDoTask(input.substring(5).strip());
    }

    public String toString() {
        if (this.status) {
            return "[T][X] " + this.description;
        } else {
            return "[T][ ] " + this.description;
        }
    }
}
