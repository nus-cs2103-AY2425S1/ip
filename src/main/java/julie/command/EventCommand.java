package julie.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import julie.exception.InvalidInputException;
import julie.exception.JulieException;
import julie.misc.Storage;
import julie.misc.UI;
import julie.task.Event;
import julie.task.Task;

/**
 * The Command that handles the creation of a event task.
 */
public class EventCommand extends Command {
    /**
     * Public constructor for a EventCommand.
     *
     * @param commandString The string to be processed.
     */
    public EventCommand(String commandString) {
        super(commandString);
    }
    @Override
    public String run(List<Task> taskList, Storage storage) throws JulieException {
        String[] tokens = this.commandString.split(" /from | /to ");
        if (tokens.length != 3) {
            throw new InvalidInputException("Event");
        }
        try {
            LocalDate from = LocalDate.parse(tokens[1]);
            LocalDate to = LocalDate.parse(tokens[2]);
            Task t = new Event(tokens[0].substring(6), from, to);
            taskList.add(t);
            storage.save(t);
            return UI.addedPrompt(t, taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Event");
        }

    }
}
