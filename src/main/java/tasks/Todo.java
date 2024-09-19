package tasks;

import exceptions.GrokInvalidUserInputException;

/**
 * A Todo class denotes a base Task with nothing else besides its description and completion status.
 */
public class Todo extends Task {
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
