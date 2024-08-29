package TheBotFather.Command;

import TheBotFather.util.Storage;
import TheBotFather.util.TaskList;
import TheBotFather.util.TheBotFatherException;
import TheBotFather.util.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException;

    public boolean isExit() {
        return false;
    }

    ;
}
