package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.Undo.Undoable;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.Task;
import ScoobyDoo.task.TaskList;

public class DeleteCommand extends Command implements Undoable {
    private final int num;
    private Task deletedTask;

    public DeleteCommand (int num) {
        this.num = num;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        taskList.undoHistory.add(this);
        this.deletedTask = taskList.deleteTask(num);
        storage.writeFile(taskList.toFileFormatString());
        return ui.response(String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list."
                , deletedTask.toString(), taskList.size()));
    }

    @Override
    public String undo(UI ui, TaskList taskList, Storage storage) {
        String addMsg = taskList.addTask(deletedTask);
        return ui.response(String.format("Undo success:\n%s",addMsg));
    }

    @Override
    public String redo(UI ui, TaskList taskList, Storage storage) {
        String executeMsg = execute(taskList, ui, storage);
        return ui.response(String.format("Redo success:\n%s", executeMsg));
    }
}
