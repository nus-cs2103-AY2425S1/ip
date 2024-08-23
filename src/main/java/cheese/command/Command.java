package cheese.command;

import cheese.CheeseException;
import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;

public class Command {
    private boolean exitChat;

    Command() {
        exitChat = false;
    }

    public boolean isExit() {
        return exitChat;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        Ui.say("Beeop... Unknown command");
    }
}
