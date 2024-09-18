package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.Undo.Undoable;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.Task;
import ScoobyDoo.task.TaskList;
import ScoobyDoo.task.Todo;

public class TodoCommand extends Command implements Undoable {
    private final String description;
    public TodoCommand (String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        taskList.undoHistory.add(this);
        Task newTask  = new Todo(description);
        String addTaskMsg = taskList.addTask(newTask);
        storage.updateFile(newTask.toFileFormatString());
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