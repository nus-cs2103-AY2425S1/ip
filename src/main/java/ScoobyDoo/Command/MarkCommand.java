package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.exception.InputFormatException;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public class MarkCommand extends Command{
    int num;

    public MarkCommand (int num) {
        this.num = num;
    }
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws InputFormatException {
        String markMsg = taskList.markTask(num);
        storage.writeFile(taskList.toFileFormatString());
        return ui.response(markMsg);
    }
}