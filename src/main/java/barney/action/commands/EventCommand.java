package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.EventTask;
import barney.ui.Ui;

/**
 * Represents a command to create an event. Extends the {@link Command} class.
 */
public class EventCommand extends Command {

    /**
     * Represents an EventCommand that performs an action related to events.
     *
     * @param argumentMap A HashMap containing the arguments for the command.
     */
    public EventCommand(HashMap<String, String> argumentMap) {
        super("event", argumentMap);
    }

    /**
     * Executes the EventCommand, which adds a new EventTask to the TaskList.
     *
     * @param tasks The TaskList to which the EventTask will be added.
     * @param ui    The Ui object used to print messages.
     * @return true if the command is executed successfully, false otherwise.
     * @throws InvalidArgumentException if the command arguments are invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags();

        String description = getParameter("description");
        String at = getParameter("from");
        String to = getParameter("to");

        EventTask newEvent = new EventTask(description, at, to);
        tasks.add(newEvent);
        ui.printAddedTask(newEvent, tasks.size());

        return true;
    }
}
