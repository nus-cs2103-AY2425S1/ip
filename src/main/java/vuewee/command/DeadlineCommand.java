package vuewee.command;

import vuewee.parser.CommandOption;
import vuewee.parser.CommandParser;

import vuewee.task.DeadlineTask;
import vuewee.task.TaskList;
import vuewee.task.TaskLocalDate;

import vuewee.TaskListUI;

class DeadlineCommand extends Command {
    public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
        CommandOption<TaskLocalDate> byOption = new CommandOption<TaskLocalDate>("by", "date", TaskLocalDate::parse);
        parser.parse(true, false, byOption);
        ui.addTask(new DeadlineTask(parser.getDescription(), byOption.getParsedValue()));
    }
}
