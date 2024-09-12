package demurebot.command;

import demurebot.task.Event;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Constructor for EventCommand.
     *
     * @param event Event task to be added to the task list.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Adds the event task to the task list and displays the success message.
     *
     * @param list Task list containing all tasks.
     * @param ui Ui to interact with the user.
     * @return Success message after adding the event task.
     */
    @Override
    public String execute(TaskList list, Ui ui) {
        list.addTask(event);
        return ui.displayAddTask(event, list.getSize());
    }
}
