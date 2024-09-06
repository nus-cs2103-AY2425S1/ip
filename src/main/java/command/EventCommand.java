package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Event;

import java.time.LocalDateTime;

/**
 * Represents a command to add an Event task to the TaskList.
 */
public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an EventCommand with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the EventCommand, adding an Event task to the TaskList, saving it to storage,
     * and displaying the event details to the user.
     *
     * @param tasks The TaskList to add the event to.
     * @param storage The Storage to save the event to.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Event e = tasks.addEvent(this.description, this.from, this.to);
        storage.appendToFile(e.toFile());
        Ui.printEvent(tasks);
    }
}
