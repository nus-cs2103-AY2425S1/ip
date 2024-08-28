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
        super(false);
        this.event = event;
    }

    /**
     * Adds the event task to the task list and displays the success message.
     *
     * @param list Task list containing all tasks.
     * @param ui Ui to interact with the user.
     */
    @Override
    public void execute(TaskList list, Ui ui) {
        list.addTask(event);
        ui.displayAddTask(event, list.getSize());
    }
}
