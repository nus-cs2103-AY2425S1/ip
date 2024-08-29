package grok;

public class Todo extends Task {
    // Use different factory methods instead.
    public Todo(String description) throws GrokInvalidUserInputException {
        super(description);
    }

    public Todo(String description, Boolean isDone) throws GrokInvalidUserInputException {
        super(description, isDone);
    }

    @Override
    public String serialize() {
        return String.join(" | ", "T", super.serialize());
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
