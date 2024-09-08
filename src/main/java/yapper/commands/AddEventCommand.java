package yapper.commands;

import yapper.components.Event;
import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.Task;
import yapper.components.TaskList;
import yapper.exceptions.YapperException;

/**
 * Class representing a command to add an event upon execution.
 */
public class AddEventCommand extends AddTaskCommand {
    public AddEventCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        String[] eventArguments = parser.getEventArguments();
        return taskList.addTask(createTaskToAdd(eventArguments));
    }

    @Override
    public String commandDescription() {
        return "Adds an event, FORMAT: event [DESCRIPTION] /from [START_DATE] /to [END_DATE]";
    }

    @Override
    public Task createTaskToAdd(String... arguments) {
        assert arguments.length == 3 : "Should have a deadline description and a deadline";
        return new Event(arguments[0], arguments[1], arguments[2]);
    }

    @Override
    public String toString() {
        return "AddEventCommand";
    }
}
