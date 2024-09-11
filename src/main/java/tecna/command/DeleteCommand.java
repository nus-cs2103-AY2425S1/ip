package tecna.command;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.ui.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            int index = parseDeleteCommand(taskList);
            taskList.deleteItem(index);
            return ui.printDeleteItemMsg(taskList, index);
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }
    }

    public int parseDeleteCommand(TaskList taskList) throws WrongFormatException {
        String[] input_words = message.split("\\s+");
        try {
            int index =  Integer.parseInt(input_words[1]) - 1;
            if (index < 1 || index > taskList.getSize()) {
                throw new WrongFormatException("delete", "Delete command should be in the format of \"delete [index of the task from 1 to " + taskList.getSize() +  "]\"");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new WrongFormatException("delete", "Delete command should be in the format of \"delete [index of the task from 1 to " + taskList.getSize() +  "]\"");
        }
    }
}
