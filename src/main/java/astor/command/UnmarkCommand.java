package astor.command;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.AstorException;
import astor.exception.MarkingTaskNotANumberException;

public class UnmarkCommand extends Command {
    private final String info;

    public UnmarkCommand(String info) {
        this.info = info;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AstorException {
        int index = -1;
        String formattedString = info.substring(6).trim();
        try {
            index = Integer.parseInt(formattedString);
        } catch (NumberFormatException e) {
            throw new MarkingTaskNotANumberException();
        }
        String output = taskList.unMarkTaskDone(index, storage);
        ui.showOutput(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
