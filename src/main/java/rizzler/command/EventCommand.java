package rizzler.command;

import rizzler.Storage;
import rizzler.task.Event;
import rizzler.task.TaskLog;

/**
 * Represents the user's instruction to create an event.
 */
public class EventCommand extends Command {
    private final Event event;

    /**
     * Constructor for an EventCommand that
     * @param eventDesc Text description of the event.
     * @param eventStart Date of event start in <code>YYYY-MM-DD</code> format.
     * @param eventEnd Date of event end in <code>YYYY-MM-DD</code> format.
     */
    public EventCommand(String eventDesc, String eventStart, String eventEnd) {
        super();
        this.event = new Event(eventDesc, eventStart, eventEnd);
    }

    /**
     * Creates an <code>Event</code> object and adds it to the taskLog, and updates storage.
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Lines to be printed in response to creation of an <code>Event</code> object.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        taskLog.addTask(event);
        storage.storeTasks(taskLog);
        return createConfirmationMessage(taskLog.getNumTasks());
    }

    private String[] createConfirmationMessage(int numTasks) {
        return new String[] {"certainly, i'll keep track of this event for you ;)",
                "\t" + event,
                "now we have " + numTasks + " tasks to work on."};
    }
}
