package ScoobyDoo.Command;

import java.time.LocalDateTime;

import ScoobyDoo.UI.UI;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.Event;
import ScoobyDoo.task.Task;
import ScoobyDoo.task.TaskList;

public class EventCommand extends Command{
    private final String description;
    private final LocalDateTime fromDateTime;
    private final LocalDateTime toDateTime;
    public EventCommand (String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this.description = description;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        Task newEvent  = new Event(description, fromDateTime, toDateTime);
        String addTaskMsg = taskList.addTask(newEvent);
        storage.updateFile(newEvent.toFileFormatString());
        return ui.response(addTaskMsg);
    }
}
