package maga;

/**
 * The {@code Command} class represents a generic command issued by the user.
 * It encapsulates the command type, an optional description, and any additional content
 * relevant to the command. The type of content can vary depending on the command.
 *
 * @param <T> The type of content associated with the command, which could be a String, Integer or a LocalDate array.
 */
public class Command<T> {
    private final String commandType;
    private String description = "";
    private final T content;

    /**
     * Constructs a {@code Command} with the specified command type and content.
     * This constructor is typically used when no description is needed.
     *
     * @param command The type of command being issued (e.g., "list", "mark", "delete").
     * @param content The additional content associated with the command.
     */
    public Command(String command, T content) {
        this.commandType = command;
        this.content = content;
    }

    /**
     * Constructs a {@code Command} with the specified command type, description, and content.
     * This constructor is used when a description is needed to further specify the command.
     *
     * @param command The type of command being issued (e.g., "todo", "event", "deadline").
     * @param description A string description that provides more context for the command.
     * @param content The additional content associated with the command.
     */
    public Command(String command, String description, T content) {
        this.commandType = command;
        this.description = description;
        this.content = content;
    }

    /**
     * Returns the type of the command.
     *
     * @return A string representing the command type.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Returns the content associated with the command.
     *
     * @return The content of the command, which could be of type {@code T}.
     */
    public T getContent() {
        return content;
    }

    /**
     * Returns the description associated with the command.
     *
     * @return A string representing the command description, or an empty string if no description is provided.
     */
    public String getDescription() {
        return description;
    }
}
