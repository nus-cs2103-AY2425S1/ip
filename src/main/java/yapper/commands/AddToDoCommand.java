package yapper.commands;

import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.Task;
import yapper.components.TaskList;
import yapper.components.ToDo;
import yapper.exceptions.YapperException;

/**
 * Class representing a command to add a todo upon execution.
 */
public class AddToDoCommand extends AddTaskCommand {
    public AddToDoCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        String description = parser.getToDoArguments();
        return taskList.addTask(createTaskToAdd(description));
    }

    @Override
    public String commandDescription() {
        return "Adds a todo, FORMAT: todo [DESCRIPTION]";
    }

    @Override
    public Task createTaskToAdd(String... arguments) {
        return new ToDo(arguments[0]);
    }

    @Override
    public String toString() {
        return "AddToDoCommand";
    }
}
