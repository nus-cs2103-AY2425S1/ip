package orion.commands;

import orion.exceptions.OrionInputException;
import orion.tasks.Todo;
import orion.utils.Parser;

public class AddTodoCommand extends AddTaskCommand {

    public AddTodoCommand(String[] command) throws OrionInputException {
        super(parseToTask(command));
    }

    private static Todo parseToTask(String[] command) throws OrionInputException {
        if (command.length < 2) {
            throw new OrionInputException("Correct syntax: todo <task>");
        } else {
            return new Todo(Parser.removeFirstWordFromString(command[1]).trim());
        }
    }
}
