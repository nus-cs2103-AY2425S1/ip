package ScoobyDoo.Command;

import java.time.LocalDateTime;

import ScoobyDoo.UI.UI;
import ScoobyDoo.Undo.Undoable;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.Deadline;
import ScoobyDoo.task.Task;
import ScoobyDoo.task.TaskList;

public class DeadlineCommand extends Command implements Undoable {
    private final String description;
    private final LocalDateTime byDateTime;
    public DeadlineCommand (String description, LocalDateTime byDateTime) {
        this.description = description;
        this.byDateTime = byDateTime;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        Task newDeadline  = new Deadline(description, byDateTime);
        String addTaskMsg = taskList.addTask(newDeadline);
        storage.updateFile(newDeadline.toFileFormatString());
        taskList.undoHistory.add(this);
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
