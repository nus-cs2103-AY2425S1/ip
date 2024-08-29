package TheBotFather.Command;

import TheBotFather.Task.Task;
import TheBotFather.util.Storage;
import TheBotFather.util.TaskList;
import TheBotFather.util.TheBotFatherException;
import TheBotFather.util.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        taskList.addTask(task);
        storage.toFile(taskList);
        ui.printCount();
    }
}
