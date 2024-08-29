public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    @Override
    public String saveToFileFormat() {
        return "T " + super.saveToFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}