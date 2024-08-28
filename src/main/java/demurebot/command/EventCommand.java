package demurebot.command;

import demurebot.task.Event;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(Event event) {
        super(false);
        this.event = event;
    }

    @Override
    public void execute(TaskList list, Ui ui) {
        list.addTask(event);
        ui.displayAddTask(event, list.getSize());
    }
}
