package elster.commands;

import elster.Storage;
import elster.TaskList;
import elster.Ui;

public class ByeCommand extends Command {
    private ByeCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }

    public static ByeCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new ByeCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        return ui.goodbyeMessage();
    }
}
