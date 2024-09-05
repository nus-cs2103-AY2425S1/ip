package vuewee.command;

import vuewee.TaskListUI;
import vuewee.parser.CommandOption;
import vuewee.parser.CommandParser;
import vuewee.task.DeadlineTask;
import vuewee.task.TaskList;
import vuewee.task.TaskLocalDate;

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
    public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
        CommandOption<TaskLocalDate> byOption = new CommandOption<TaskLocalDate>("by", "date", TaskLocalDate::parse);
        parser.parse(true, false, byOption);
        ui.addTask(new DeadlineTask(parser.getDescription(), byOption.getParsedValue()));
    }
}
