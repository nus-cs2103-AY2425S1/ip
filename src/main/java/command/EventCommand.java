package command;

import parser.Parser;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents the event command which creates a new
 * Event task.
 */
public class EventCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(String[] parts, TaskList list, Ui ui, Storage storage, Parser parser) {
        try {
            Task eventTask = parser.parseEventTask(parts[1]);
            list.addTask(eventTask);

            return ui.affirm() + eventTask.getDescription() +
                    "\n" + String.format("Now you have %d tasks in the list\n", list.size());
        } catch (StringIndexOutOfBoundsException e) {
            return ui.EventOutOfBoundsExceptionMessage();
        } catch (DateTimeParseException e) {
            return ui.EventDateTimeParseExceptionMessage();
        }
    }
}
