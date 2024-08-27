public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toText() {
        return "T | " + super.toText();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
