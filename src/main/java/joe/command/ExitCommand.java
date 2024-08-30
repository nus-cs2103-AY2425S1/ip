package joe.command;

import joe.JoeException;
import joe.Storage;
import joe.Ui;
import joe.task.TaskList;

import java.io.IOException;
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JoeException {
        ui.printExit();
        try {
            storage.saveData(taskList);
        } catch (IOException e) {
            throw new JoeException("Failed to save");
        }
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
