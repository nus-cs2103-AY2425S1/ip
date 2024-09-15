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
        assert taskList != null : "taskList must not be null";
        assert ui != null : "ui must not be null";
        assert storage != null : "storage must not be null";

        String[] formattedString = info.substring(4).trim().split("\\s+");
        int[] indexList = new int[formattedString.length];
        try {
            for (int i = 0; i < formattedString.length; i++) {
                int index = Integer.parseInt(formattedString[i]);
                indexList[i] = index;
            }
        } catch (NumberFormatException e) {
            throw new MarkingTaskNotANumberException();
        }
        String output = taskList.markListDone(storage, indexList);
        ui.showOutput(output);
        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
