package julie.command;

import julie.exception.InvalidInputException;
import julie.exception.JulieException;
import julie.misc.Storage;
import julie.misc.UI;
import julie.task.Event;
import julie.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EventCommand extends Command {
    public EventCommand(String commandString) {
        super(commandString);
    }
    @Override
    public void run(List<Task> taskList) throws JulieException {
        String[] tokens = this.commandString.split(" /from | /to ");
        if (tokens.length != 3) {
            throw new InvalidInputException("Event");
        }
        try {
            LocalDate from = LocalDate.parse(tokens[1]);
            LocalDate to = LocalDate.parse(tokens[2]);
            Task t = new Event(tokens[0].substring(6), from, to);
            taskList.add(t);
            UI.addedPrompt(t, taskList);
            Storage.save(t);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Event");
        }

    }
}
