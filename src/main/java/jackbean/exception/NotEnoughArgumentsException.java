package jackbean.exception;

import jackbean.task.TaskType;

/**
 * Represents an exception thrown when the user does not provide enough arguments for a task in the JackBean chatbot.
 * This JavaDoc was written by GitHub Copilot.
 */
public class NotEnoughArgumentsException extends Exception {
    private TaskType type;

    /**
     * Constructs a NotEnoughArgumentsException with a default message.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param type The type of task that the user did not provide enough arguments for.
     * @param message The message to be shown.
     */
    public NotEnoughArgumentsException(String type, String message) {
        super(message);
        if (type.equalsIgnoreCase("deadline")) {
            this.type = TaskType.DEADLINE;
        } else if (type.equalsIgnoreCase("event")) {
            this.type = TaskType.EVENT;
        } else if (type.equalsIgnoreCase("todo")) {
            this.type = TaskType.TODO;
        }
    }

    /**
     * Constructs a NotEnoughArgumentsException with a default message.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param type The type of task that the user did not provide enough arguments for.
     */
    public NotEnoughArgumentsException(String type) {
        new NotEnoughArgumentsException(type, "not enough arguments");
    }

    @Override
    public String toString() {
        return switch (type) {
        case DEADLINE ->
                "Yo Homieee, there are not enough arguments for deadline!\n"
                        + "deadline should be of this format: deadline <description> /by <date>";
        case EVENT ->
                "Yo Homieee, there are not enough arguments for event!\n"
                        + "event should be of this format: event <description> /from <date> /to <date>";
        case TODO ->
                "Yo Homieee, there are not enough arguments for todo!\n"
                        + "todo should be of this format: todo <description>";
        };
    }
}
