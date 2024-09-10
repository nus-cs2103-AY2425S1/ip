package vuewee.command;

import vuewee.parser.CommandOption;
import vuewee.parser.CommandParser;
import vuewee.task.EventTask;
import vuewee.task.TaskList;
import vuewee.task.TaskLocalDate;
import vuewee.ui.TaskListUi;

/**
 * Represents a command that adds an event task to the task list which has a
 * from and to date.
 */
class EventCommand extends Command {
    /**
     * Executes the event command by parsing the input, creating a new event task,
     * and adding it to the task list
     *
     * @param ui       The user interface for displaying messages.
     * @param taskList The task list to add the event task to.
     * @param parser   The command parser for parsing command options.
     */
    public void executeCommand(TaskListUi ui, TaskList taskList, CommandParser parser) {
        CommandOption<TaskLocalDate> fromOption = new CommandOption<TaskLocalDate>("from", "fromDate yyyy-mm-dd",
                TaskLocalDate::parse);
        CommandOption<TaskLocalDate> toOption = new CommandOption<TaskLocalDate>("to", "toDate yyyy-mm-dd",
                TaskLocalDate.createParserWithFrom(na -> fromOption.getParsedValue()));
        parser.parse(true, false, fromOption, toOption);
        ui.addTask(new EventTask(parser.getDescription(), fromOption.getParsedValue(), toOption.getParsedValue()));
    }
}
