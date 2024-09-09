package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.DeleteTaskNumberException;

/**
 * Represents a command for deleting a task.
 *
 * Allows the command to be executed to delete task from the taskList, updates storage and send reply to
 * the ui, and shows that this is not a terminal command.
 *
 * @author Choi Yi Hao
 */
public class DeleteCommand extends Command {
    private String info;

    public DeleteCommand(String info) {
        this.info = info;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AstorException {
        String formattedString = info.substring(6).trim();
        int indexC = -1;
        try {
            indexC = Integer.parseInt(formattedString);
        } catch (NumberFormatException e) {
            throw new DeleteTaskNumberException();
        }
        String output = taskList.deleteTask(indexC, storage);
        ui.showOutput(output);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
