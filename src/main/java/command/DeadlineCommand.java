package command;

import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents the deadline command which creates a new
 * deadline task.
 */
public class DeadlineCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        try {
            Task deadlineTask = parser.parseDeadlineTask(parts[1]);
            list.addTask(deadlineTask);
            return ui.affirm() + deadlineTask.getDescription() +
                    "\n" + String.format("Now you have %d tasks in the list\n", list.size());
        } catch (StringIndexOutOfBoundsException e) {
            return ui.DeadlineOutOfBoundsExceptionMessage();
        } catch (DateTimeParseException e) {
            return ui.DeadlineDateTimeParseExceptionMessage();
        }
    }
}
