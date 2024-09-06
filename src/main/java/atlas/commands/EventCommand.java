package atlas.commands;

import java.time.LocalDateTime;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Event;
import atlas.tasks.TaskList;

/**
 * Creates an event when this class is instantiated.
 */
public class EventCommand extends Command {
    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Instantiates the event command.
     *
     * @param name The name or description of the deadline.
     * @param startTime The date and time to start this event at.
     * @param endTime The date and time to finish this event by.
     */
    public EventCommand(String name, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates a new event and adds it to the task list.
     *
     * @param tasks The current list of tasks in the chatbot.
     * @param storage The storage object the chatbot uses to store and load tasks
     * @return String The message returned to be displayed on the chatbot GUI.
     * @throws AtlasException The exception to be thrown in the event of any error.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AtlasException {
        Event event = new Event(this.name, this.startTime, this.endTime);
        tasks.add(event);
        storage.save();
        return String.format("Got it. I've added this task:\n\t%s\n Now you have %s tasks in the list.",
                event, tasks.size());
    }
}
