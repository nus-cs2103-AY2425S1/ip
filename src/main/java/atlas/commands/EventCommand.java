package atlas.commands;

import atlas.exceptions.AtlasException;
import atlas.storage.Storage;
import atlas.tasks.Event;
import atlas.tasks.TaskList;
import atlas.ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    public EventCommand(String name, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @param tasks
     * @param ui
     * @param storage
     * @throws AtlasException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtlasException {
        Event event = new Event(this.name, this.startTime, this.endTime);
        tasks.add(event);
        storage.save();
        String message = String.format("Got it. I've added this task:\n\t%s\n Now you have %s tasks in the list.",
                event, tasks.size());
        ui.print(message);
    }
}
