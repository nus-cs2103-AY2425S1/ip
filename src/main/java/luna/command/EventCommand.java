package luna.command;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Event;
import luna.task.Task;

/**
 * Represents a command to add event to list of tasks.
 */
public class EventCommand implements Command {
    private final Event event;
    private final Command previousCommand;

    /**
     * Creates a command to add event to list.
     *
     * @param event Event to be added to list.
     */
    public EventCommand(Event event, Command previousCommand) {
        this.event = event;
        this.previousCommand = previousCommand;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.addTask(event, storage);
    }

    @Override
    public String undo(TaskList tasks, Storage storage) throws LunaException {
        int taskNumber = tasks.getTasks().indexOf(event);
        Task deleted = tasks.deleteTask(taskNumber, storage);
        return ">>> undo 'event' command\n"
                + "I've removed this task:\n"
                + "  " + deleted + "\n"
                + "Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
    }
}
