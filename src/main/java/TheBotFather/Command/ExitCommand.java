package TheBotFather.Command;

import TheBotFather.util.Storage;
import TheBotFather.util.TaskList;
import TheBotFather.util.TheBotFatherException;
import TheBotFather.util.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        ui.printGoodBye();
        storage.toFile(taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
