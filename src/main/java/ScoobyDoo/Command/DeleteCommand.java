package ScoobyDoo.Command;

import ScoobyDoo.UI.UI;
import ScoobyDoo.storage.Storage;
import ScoobyDoo.task.TaskList;

public class DeleteCommand extends Command{
//    try {
//        int i = TaskList.getDeleteNumber(input);
//        return ui.printFormattedResponse(taskList.deleteTask(i));
//    } catch (
//    InputFormatException e) {
//        return ui.printErrorMessage(e.getMessage());
//    }
    int num;

    public DeleteCommand (int num) {
        this.num = num;
    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) {
        String deleteMsg = taskList.deleteTask(num);
        storage.writeFile(taskList.toFileFormatString());
        return ui.response(deleteMsg);
    }
}
