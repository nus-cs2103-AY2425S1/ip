package rizzler.command;

import rizzler.Storage;
import rizzler.task.Event;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerSpeech;

public class EventCommand extends Command {
    private final Event event;

    public EventCommand(String eventDesc, String eventStart, String eventEnd) {
        super();
        this.event = new Event(eventDesc, eventStart, eventEnd);
    }

    @Override
    public void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog) {
        speech.say();
        taskLog.addTask(event);
        storage.storeTasks(taskLog);
        speech.say("certainly, i'll keep track of this event for you ;)");
        speech.say("\t" + event);
        speech.say("now we have " + taskLog.getNumTasks() + " tasks to work on.");
        speech.say();
    }
}
