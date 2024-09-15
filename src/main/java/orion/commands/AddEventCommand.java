package orion.commands;

import java.util.Arrays;

import orion.exceptions.OrionException;
import orion.exceptions.OrionInputException;
import orion.tasks.Event;
import orion.utils.Parser;

public class AddEventCommand extends AddTaskCommand {
    public AddEventCommand(String[] command) throws OrionException {
        super(parseToTask(command));
    }

    private static Event parseToTask(String[] command) throws OrionException {
        if (command.length != 3 || !command[1].matches("^from.*$")
                || !command[2].matches("^to.*$")) {
            throw new OrionInputException("Correct syntax: event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
        } else {
            String[] mapped = Arrays.stream(command)
                    .map(Parser::removeFirstWordFromString)
                    .toArray(String[]::new);
            return new Event(mapped[0].trim(), mapped[1].trim(), mapped[2].trim());
        }
    }
}
