package assistinator.commands;

import java.time.format.DateTimeFormatter;

import assistinator.AssistinatorException;
import assistinator.Storage;
import assistinator.TaskList;

/**
 * Represents command given to chat bot.
 */
public abstract class Command {
    protected String input;
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes command.
     * @param tasks Task list.
     * @param storage Storage.
     * @return Response to command.
     * @throws AssistinatorException If an error occurs during execution of command.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws AssistinatorException;

    /**
     * Parses the input string to extract and return an index.
     * This method expects the input to be in the format "command index",
     * where index is a positive integer.
     *
     * @return The parsed index as an integer, adjusted to be zero-based (subtracts 1 from the parsed number).
     * @throws AssistinatorException If the input format is invalid or the index cannot be parsed.
     */
    public int parseIndex() throws AssistinatorException {
        try {
            return Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new AssistinatorException("Please provide a valid task number");
        }
    }

    /**
     * Parses the input string to extract and return a keyword.
     *
     * @return Keyword for find command.
     * @throws AssistinatorException If keyword is empty or blank spaces.
     */
    public String parseKeyword() throws AssistinatorException {
        String keyword = input.substring(input.indexOf(' ') + 1);
        if (keyword.trim().equalsIgnoreCase("find") || keyword.trim().isEmpty()) {
            throw new AssistinatorException("Keyword cannot be empty");
        }
        return keyword;
    }
}
