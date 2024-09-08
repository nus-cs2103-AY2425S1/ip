package yapper.commands;

import yapper.components.Deadline;
import yapper.components.Parser;
import yapper.components.Storage;
import yapper.components.Task;
import yapper.components.TaskList;
import yapper.exceptions.YapperException;

/**
 * Class representing a command to add a deadline upon execution.
 */
public class AddDeadlineCommand extends AddTaskCommand {
    public AddDeadlineCommand() {
        super();
    }

    @Override
    public String execute(Parser parser, TaskList taskList, Storage storage) throws YapperException {
        String[] deadlineArguments = parser.getDeadlineArguments();
        return taskList.addTask(createTaskToAdd(deadlineArguments));
    }

    @Override
    public Task createTaskToAdd(String... arguments) {
        assert arguments.length == 2 : "Should have a deadline description and a deadline";
        return new Deadline(arguments[0], arguments[1]);
    }
}
