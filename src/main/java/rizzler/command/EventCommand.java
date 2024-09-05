package rizzler.command;

import rizzler.Storage;
import rizzler.task.Event;
import rizzler.task.TaskLog;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(String eventDesc, String eventStart, String eventEnd) {
        super();
        this.event = new Event(eventDesc, eventStart, eventEnd);
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        taskLog.addTask(event);
        storage.storeTasks(taskLog);
        return new String[] {"certainly, i'll keep track of this event for you ;)",
                "\t" + event,
                "now we have " + taskLog.getNumTasks() + " tasks to work on."};
    }
}
