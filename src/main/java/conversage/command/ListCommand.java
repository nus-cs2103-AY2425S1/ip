
package conversage.command;


import conversage.exception.ConverSageException;
import conversage.storage.Storage;
import conversage.task.TaskList;
import conversage.ui.Ui;


public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ConverSageException {
        String toRet = "";
        ui.showLine();
        if (tasks.size() == 0) {
            toRet = "The path to wisdom is clear, for you have no tasks to cloud your mind. "
                        + "Enjoy this moment of tranquility.";
            ui.showMessage(toRet);
        }
        for (int i = 1; i <= tasks.size(); i++) {
            toRet = toRet + i + ". " + tasks.getTask(i - 1).toString() + "\n";
            ui.showMessage(i + ". " + tasks.getTask(i - 1).toString());
        }
        ui.showLine();
        return toRet;
    }
}