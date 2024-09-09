package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.MarkingTaskNotANumberException;

/**
 * Represents a command to mark tasks as completed.
 *
 * When executed, it looks for the task in the taskList, mark the task as done and updates the storage.
 * This is not a terminal command.
 *
 * @author Choi Yi Hao
 */
public class MarkCommand extends Command {
    private final String info;

    public MarkCommand(String info) {
        this.info = info;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AstorException {
        int indexB = 0;
        String formattedString = info.substring(4).trim();
        try {
            indexB = Integer.parseInt(formattedString);
        } catch (NumberFormatException e) {
            throw new MarkingTaskNotANumberException();
        }
        String output = taskList.markTaskDone(indexB, storage);
        ui.showOutput(output);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
