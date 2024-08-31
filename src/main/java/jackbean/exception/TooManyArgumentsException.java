package jackbean.exception;

import jackbean.task.TaskType;

/**
 * Represents an exception thrown when the user provides too many arguments for a task in the JackBean chatbot.
 * This JavaDoc was written by GitHub Copilot.
 */
public class TooManyArgumentsException extends Exception {
    public TaskType type;

    /**
     * Constructs a TooManyArgumentsException with a default message.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param type The type of task that the user provided too many arguments for.
     * @param message The message to be shown.
     */
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

    /**
     * Constructs a TooManyArgumentsException with a default message.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param type The type of task that the user provided too many arguments for.
     */
    public TooManyArgumentsException(String type) {
        new TooManyArgumentsException(type, "too many arguments");
    }

    @Override
    public String toString() {
        return switch (type) {
            case DEADLINE ->
                    "Yo Homieee, there are too many arguments for deadline!\ndeadline should be of this format: deadline <description> /by <date>";
            case EVENT ->
                    "Yo Homieee, there are too many arguments for event!\nevent should be of this format: event <description> /from <date> /to <date>";
            case TODO ->
                    "Yo Homieee, there are too many arguments for todo!\ntodo should be of this format: todo <description>";
        };
    }
}
