package drbrown.parsing;

import drbrown.command.AddCommand;
import drbrown.command.Command;
import drbrown.task.Task;
import drbrown.task.Todo;
import drbrown.utils.DrBrownException;

/**
 * A parser that handles the "todo" command input.
 * Responsible for parsing the input to create a {@link Todo} task.
 */
public class TodoParser {

    /**
     * Parses the input to create an {@link AddCommand} that adds a {@link Todo} task.
     *
     * @param inputSplit An array of strings containing the user's input split by spaces.
     * @return An instance of {@link AddCommand} containing the {@link Todo} task.
     * @throws DrBrownException If the input is invalid, such as missing the description.
     */
    public static Command parse(String[] inputSplit) throws DrBrownException {
        assert inputSplit != null : "Input string array should not be null";
        try {
            if (inputSplit[1].trim().isEmpty()) {
                throw new DrBrownException("Great Scott! You can't add a to-do without a "
                        + "description!\n\nUse the format: todo {description}");
            }
            Task todo = new Todo(false, inputSplit[1].trim());
            return new AddCommand(todo);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DrBrownException("Great Scott! You can't add a to-do without a description!"
                    + "\n\nUse the format: todo {description}");
        }
    }

}
