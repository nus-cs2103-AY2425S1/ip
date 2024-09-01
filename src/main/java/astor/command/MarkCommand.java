package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.MarkingTaskNotANumberException;

public class MarkCommand extends Command {
    private final String info;

    public MarkCommand(String info) {
        this.info = info;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AstorException {
        int indexB = 0;
        String formattedString = info.substring(4).trim();
        try {
            indexB = Integer.parseInt(formattedString);
        } catch (NumberFormatException e) {
            throw new MarkingTaskNotANumberException();
        }
        String output = taskList.markTaskDone(indexB, storage);
        ui.showOutput(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
