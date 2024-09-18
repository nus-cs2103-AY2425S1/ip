package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.Undo.Undoable;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public class MarkCommand extends Command implements Undoable {
    int num;

    public MarkCommand (int num) {
        this.num = num;
    }
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws InputFormatException {
        taskList.undoHistory.add(this);
        String markMsg = taskList.markTask(num);
        storage.writeFile(taskList.toFileFormatString());
        return ui.response(markMsg);
    }

    @Override
    public String undo(UI ui, TaskList taskList, Storage storage) {
        try {
            String unmarkMsg = taskList.unMarkTask(num);
            return ui.response(String.format("Undo success:\n%s",unmarkMsg));
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