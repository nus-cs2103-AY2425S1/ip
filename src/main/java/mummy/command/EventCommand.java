package mummy.command;

import java.util.HashMap;

import mummy.task.Event;
import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.ui.Ui;
import mummy.utility.Storage;

public class EventCommand extends Command {

    public EventCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws MummyException {
        String description = this.getArgument("description", "");
        String from = this.getArgument("from");
        String to = this.getArgument("to");

        if (from == null || to == null) {
            throw new MummyException("/from and /to are required");
        }

        addTask(new Event(description, from, to), taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
