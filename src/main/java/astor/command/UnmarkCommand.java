package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.MarkingTaskNotANumberException;

/**
 * Represents a class for marking a task as not completed.
 *
 * When executed, it looks for the task in the taskList and marks it as undone, updates the storage and sends reply
 * to user. This is not a terminal command.
 *
 * @author Choi Yi Hao
 */
public class UnmarkCommand extends Command {
    private final String info;

    public UnmarkCommand(String info) {
        this.info = info;
    }



    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AstorException {
        int index = -1;
        String formattedString = info.substring(6).trim();
        try {
            index = Integer.parseInt(formattedString);
        } catch (NumberFormatException e) {
            throw new MarkingTaskNotANumberException();
        }
        String output = taskList.unMarkTaskDone(index, storage);
        ui.showOutput(output);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
