package evan.command;

import evan.main.Storage;
import evan.main.TaskList;
import evan.main.Ui;
import evan.task.Event;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(description, from, to);
        taskList.add(event);
        ui.showSuccess("Got it. I've added this event:\n" + event);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
