public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    public static Todo generateFromString(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        return new Todo(string);
    }
}
