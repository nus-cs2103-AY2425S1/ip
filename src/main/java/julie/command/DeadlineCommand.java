package julie.command;
import julie.exception.InvalidInputException;
import julie.exception.JulieException;
import julie.misc.Storage;
import julie.misc.UI;
import julie.task.Deadline;
import julie.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The command that handles the creation of a Deadline Task.
 */
public class DeadlineCommand extends Command {
    /**
     * Public constructor for a DeadlineCommand.
     *
     * @param commandString The string to be processed.
     */
    public DeadlineCommand(String commandString) {
        super(commandString);
    }
    @Override
    public void run(List<Task> taskList) throws JulieException {
        String[] tokens = this.commandString.split(" /by ");
        if (tokens.length != 2) {
            throw new InvalidInputException("Deadline");
        }
        try {
            LocalDate due = LocalDate.parse(tokens[1]);
            Task t = new Deadline(tokens[0].substring(9), due);
            taskList.add(t);
            UI.addedPrompt(t, taskList);
            Storage.save(t);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Deadline");
        }
    }
}
