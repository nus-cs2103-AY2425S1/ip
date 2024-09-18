package mummy.command;

import java.util.HashMap;

import mummy.task.Event;
import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Represents a command to add an event task.
 * Inherits from the Command class.
 */
public final class EventCommand extends AddCommand {

    public EventCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MummyException {
        String description = this.getArgument("description", "");
        String from = this.getArgument("from");
        String to = this.getArgument("to");

        if (from == null || to == null) {
            throw new MummyException("/from and /to are required");
        }

        return this.addTask(new Event(description, from, to), taskList, storage);
    }
}
