package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.Undo.Undoable;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public class UnMarkCommand extends Command implements Undoable {
    int num;

    public UnMarkCommand (int num) {
        this.num = num;
    }
    @Override
     public String execute(TaskList taskList, UI ui, Storage storage) throws InputFormatException {
        taskList.undoHistory.add(this);
        String unMarkMsg = taskList.unMarkTask(num);
        storage.writeFile(taskList.toFileFormatString());
        return ui.response(unMarkMsg);
    }

    @Override
    public String undo(UI ui, TaskList taskList, Storage storage) {
        try {
            String markMsg = taskList.markTask(num);
            return ui.response(String.format("Undo success:\n%s",markMsg));
        } catch (InputFormatException e) {
            return ui.printErrorMessage("Undo cannot be done");
        }
    }

    @Override
    public String redo(UI ui, TaskList taskList, Storage storage){
        try {
            return execute(taskList, ui, storage);
        } catch (InputFormatException e) {
            return ui.printErrorMessage("Cant redo");
        }
    }
}