package nuffle.command;

import nuffle.storage.Storage;
import nuffle.task.TaskList;
import nuffle.ui.Ui;

public class ByeCommand extends Command {
    private final String userInput;

    public ByeCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.byeMessage();
    }
}
