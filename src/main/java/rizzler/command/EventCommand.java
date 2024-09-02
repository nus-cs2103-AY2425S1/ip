package rizzler.command;

public class EventCommand extends Command {
    private final Event event;

    EventCommand(String eventDesc, String eventStart, String eventEnd) {
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
