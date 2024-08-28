package oyster.tasks;

import oyster.exceptions.TaskFieldException;

public class ToDoTask extends Task {
    public static final String FILE_SYMBOL = "T";

    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask fromInput(String description) {
        description = description.trim();

        if (description.isEmpty()) {
            throw new TaskFieldException("Description");
        }

        return new ToDoTask(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String[] compose() {
        return new String[] {
                FILE_SYMBOL,
                isMarked() ? "1" : "0",
                getDescription()
        };
    }
}
