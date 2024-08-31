package jackbean.exception;

import jackbean.task.TaskType;

public class TooManyArgumentsException extends Exception {
    private TaskType type;

    public TooManyArgumentsException(String type, String message) {
        super(message);
        if (type.equalsIgnoreCase("deadline")) {
            this.type = TaskType.DEADLINE;
        } else if (type.equalsIgnoreCase("event")) {
            this.type = TaskType.EVENT;
        } else if (type.equalsIgnoreCase("todo")) {
            this.type = TaskType.TODO;
        }
    }

    public TooManyArgumentsException(String type) {
        new TooManyArgumentsException(type, "too many arguments");
    }

    @Override
    public String toString() {
        return switch (type) {
        case DEADLINE ->
                "Yo Homieee, there are too many arguments for deadline!\n"
                        + "deadline should be of this format: deadline <description> /by <date>";
        case EVENT ->
                "Yo Homieee, there are too many arguments for event!\n"
                        + "event should be of this format: event <description> /from <date> /to <date>";
        case TODO ->
                "Yo Homieee, there are too many arguments for todo!\n"
                        + "todo should be of this format: todo <description>";
        };
    }
}
