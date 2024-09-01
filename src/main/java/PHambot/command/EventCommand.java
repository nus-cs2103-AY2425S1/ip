package PHambot.command;

import PHambot.task.Event;
import PHambot.utils.Utilities;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final Event event;

    public EventCommand(String task, String date, String time) {
        this.event = new Event(task, date, time);
    }

    public EventCommand(String task, String date) {
        this.event = new Event(task, date);
    }


    @Override
    public boolean executeCommand() {
        Utilities.OutlineMessage("Added: " + this.event.toString());
        return Command.taskList.addTask(this.event);
    }
}
