package darkpool.parser;

import static darkpool.parser.validator.ValidateTodo.validate;

import darkpool.DarkpoolException;
import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.task.Todo;

/**
 * Parses the user input for a Todo task.
 */
public class TodoParser {

    /**
     * Parses the user input for a Todo task.
     *
     * @param userInput The user input to be parsed.
     * @return The AddCommand to add the Todo task.
     * @throws DarkpoolException If the user input is invalid.
     */
    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new AddCommand(getTodoFromInput(userInput));
    }

    private static Todo getTodoFromInput(String[] userInput) {
        return new Todo(userInput[1], false);
    }

}
