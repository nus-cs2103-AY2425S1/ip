package mummy.command;

import java.util.HashMap;

import mummy.task.TaskList;
import mummy.task.ToDo;
import mummy.ui.MummyException;
import mummy.utility.Storage;

/**
 * Represents a command to add a new ToDo task.
 * Inherits from the Command class.
 */
public final class ToDoCommand extends AddCommand {

    public ToDoCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws MummyException {
        String description = this.getArgument("description", "");
        return this.addTask(new ToDo(description), taskList, storage);
    }
}
