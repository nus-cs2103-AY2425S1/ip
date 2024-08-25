public class Todo extends Task {
    public Todo(String line) {
        super(line);
    }

    @Override
    String getSaveFormat() {
        return String.format("T | %d | %s", super.intComplete(), super.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", completedStringRepresentation(), super.getDescription());
    }
}
