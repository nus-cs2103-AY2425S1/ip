package qwerty.task;

import java.util.Arrays;
import java.util.List;

/**
 * This class encapsulates a Todo type task.
 * A Todo task is not attached to any date or time.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public List<String> getAllDetails() {
        return Arrays.asList(
                "T",
                getStatusIcon(),
                getDescription()
        );
    }
}
