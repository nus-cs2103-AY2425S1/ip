package tasks;

import exceptions.CorruptedTaskStringException;
import exceptions.EmptyArgumentException;

public class Todo extends Task {
    public Todo(boolean completed, String description) throws EmptyArgumentException {
        super(completed, description);
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
    }
    public Todo(String description) throws EmptyArgumentException {
        this(false, description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    public static Todo fromDataString(String taskString) throws CorruptedTaskStringException, EmptyArgumentException {
        if (!taskString.matches("T \\| [10] \\| (.*?)")) {
            throw new CorruptedTaskStringException();
        }

        String[] arguments = taskString.split(" \\| ");

        boolean completed = arguments[1].equals("1");
        String description = arguments[2];

        return new Todo(completed, description);
    }

    @Override
    public String toDataString() {
        return "T | " + getCompletedAndDescription();
    }
}
