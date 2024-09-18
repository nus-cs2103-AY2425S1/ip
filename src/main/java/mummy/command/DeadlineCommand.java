package mummy.command;

import java.util.HashMap;

import mummy.task.Deadline;
import mummy.task.TaskList;
import mummy.ui.MummyException;
import mummy.utility.Storage;


/**
 * Represents a command to add a deadline task.
 * Inherits from the Command class.
 */
public final class DeadlineCommand extends AddCommand {

    public DeadlineCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MummyException {
        String description = this.getArgument("description", "");
        String dueBy = this.getArgument("by");

        if (dueBy == null) {
            throw new MummyException("/by is required");
        }

        return this.addTask(new Deadline(description, dueBy), taskList, storage);
    }
}
