package vuewee.command;

import vuewee.parser.CommandOption;
import vuewee.parser.CommandParser;
import vuewee.parser.description.StringDescriptionParser;
import vuewee.task.DeadlineTask;
import vuewee.task.TaskList;
import vuewee.task.TaskLocalDate;
import vuewee.ui.TaskListUi;

/**
 * Represents a command to add a deadline task to the task list which has a by
 * date.
 */
class DeadlineCommand extends Command {
    /**
     * Executes the deadline command by parsing the input, creating a new deadline
     * task, and adding it to the task list.
     *
     * @param ui       The user interface for displaying messages.
     * @param taskList The task list to add the task to.
     * @param parser   The command parser for parsing command options.
     */
    @Override
    public void executeCommand(TaskListUi ui, TaskList taskList, CommandParser parser) {
        assert ui != null : "UI cannot be null";
        assert taskList != null : "Task list cannot be null";
        assert parser != null : "Parser cannot be null";

        CommandOption<TaskLocalDate> byOption = new CommandOption<TaskLocalDate>("by", "date yyyy-mm-dd",
                TaskLocalDate::parse);
        String desc = parser.<String>parse(new StringDescriptionParser(), byOption);
        ui.addTask(new DeadlineTask(desc, byOption.getParsedValue()));
    }
}
