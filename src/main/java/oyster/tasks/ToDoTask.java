package oyster.tasks;

import oyster.exceptions.TaskFieldException;

public class ToDoTask extends Task {
    public static final String FILE_SYMBOL = "T";

    /**
     * @param description The description of the Task
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * @param input The string to parse into a ToDoTask
     * @return ToDoTask object
     */
    public static ToDoTask fromInput(String input) {
        input = input.trim();

        if (input.isEmpty()) {
            throw new TaskFieldException("Description");
        }

        return new ToDoTask(input);
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
