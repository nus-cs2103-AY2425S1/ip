package luna.command;

import java.util.ArrayList;

import luna.Storage;
import luna.TaskList;
import luna.task.Event;
import luna.task.Task;

/**
 * Represents a command to add event to list of tasks.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Creates a command to add event to list.
     *
     * @param event Event to be added to list.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.addTask(event, storage);
    }
}
