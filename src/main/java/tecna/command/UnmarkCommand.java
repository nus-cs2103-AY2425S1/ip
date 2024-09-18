package tecna.command;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.ui.Ui;

public class UnmarkCommand extends Command {
    public UnmarkCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        try {
            int index = parseMarkCommand(taskList.getSize());
            taskList.unmark(index);
            return ui.printUnmarkMsg(taskList.getTask(index));
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }
    }

    public int parseMarkCommand(int taskListSize) throws WrongFormatException {
        String[] input_words = message.split("\\s+");

        if (input_words.length != 2) {
            throw new WrongFormatException("unmark", "Unmark command should be in the format of \"unmark [index of the task from 1 to " + taskListSize +  "]\"");
        }

        try {
            int index =  Integer.parseInt(input_words[1]) - 1;
            if (index < 1 || index > taskListSize) {
                throw new WrongFormatException("unmark", "Unmark command should be in the format of \"unmark [index of the task from 1 to " + taskListSize +  "]\"");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new WrongFormatException("unmark", "Unmark command should be in the format of \"unmark [index of the task from 1 to " + taskListSize +  "]\"");
        }
    }
}
