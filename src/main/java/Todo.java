public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[📝 ToDo] " + super.toString() + " - Let's get this done! 💪";
    }
}
