package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public class UnMarkCommand extends Command{
    int num;

    public UnMarkCommand (int num) {
        this.num = num;
    }
    @Override
     public String execute(TaskList taskList, UI ui, Storage storage) throws InputFormatException {
        String unMarkMsg = taskList.unMarkTask(num);
        storage.writeFile(taskList.toFileFormatString());
        return ui.response(unMarkMsg);
    }
}