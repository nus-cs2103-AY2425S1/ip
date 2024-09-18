package ScoobyDoo.Command;

import java.time.LocalDateTime;

import ScoobyDoo.UI.UI;
import ScoobyDoo.Undo.Undoable;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.Event;
import ScoobyDoo.task.Task;
import ScoobyDoo.task.TaskList;

public class EventCommand extends Command implements Undoable {
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
        taskList.undoHistory.add(this);
        Task newEvent  = new Event(description, fromDateTime, toDateTime);
        String addTaskMsg = taskList.addTask(newEvent);
        storage.updateFile(newEvent.toFileFormatString());
        return ui.response(addTaskMsg);
    }

    @Override
    public String undo(UI ui, TaskList taskList, Storage storage) {
        String deleteMsg = taskList.deleteLast();
        return ui.response(String.format("Undo success:\n%s",deleteMsg));
    }

    @Override
    public String redo(UI ui, TaskList taskList, Storage storage) {
        String executeMsg = execute(taskList, ui, storage);
        return ui.response(String.format("Redo success:\n%s", executeMsg));
    }
}
