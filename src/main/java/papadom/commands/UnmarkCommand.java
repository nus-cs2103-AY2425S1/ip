package papadom.commands;

import papadom.Exceptions.NoTaskNumberException;
import papadom.Exceptions.WrongTaskNumberException;
import papadom.Storage.Storage;
import papadom.Storage.TaskList;
import papadom.Ui;

public class UnmarkCommand extends Command {
    private String text;
    public UnmarkCommand(String text) {
        this.text = text;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws WrongTaskNumberException, NoTaskNumberException {
        ui.output(taskList.unmarkTask(text));
    }
}