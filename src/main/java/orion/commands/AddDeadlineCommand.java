package orion.commands;

import java.util.Arrays;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;
import orion.tasks.Deadline;
import orion.utils.Parser;

public class AddDeadlineCommand extends AddTaskCommand {
    public AddDeadlineCommand(String[] command) throws OrionException {
        super(parseToTask(command));
    }

    private static Deadline parseToTask(String[] command) throws OrionException {
        if (command.length != 2 || !command[1].matches("^by.*$")) {
            throw new OrionInputException("Correct syntax: deadline <task> /by <yyyy-mm-dd>");
        } else {
            String[] mapped = Arrays.stream(command)
                    .map(Parser::removeFirstWordFromString)
                    .toArray(String[]::new);
            return new Deadline(mapped[0].trim(), mapped[1].trim());
        }
    }
}
