package darkpool.parser;

import darkpool.command.AddCommand;
import darkpool.command.Command;
import darkpool.DarkpoolException;
import darkpool.task.Todo;

import static darkpool.parser.validator.ValidateTodo.validate;


public class TodoParser {

    public static Command parse(String[] userInput) throws DarkpoolException {
        validate(userInput);
        return new AddCommand(getTodoFromInput(userInput));
    }

    private static Todo getTodoFromInput(String[] userInput) {
        return new Todo(userInput[1], false);
    }

}
