package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.parser.description.DateDescriptionParser;
import vuewee.task.TaskList;
import vuewee.task.TaskLocalDate;
import vuewee.ui.TaskListUi;

public class ScheduleCommand extends Command {
    @Override
    public void executeCommand(TaskListUi ui, TaskList taskList, CommandParser parser) {
        TaskLocalDate date = parser.<TaskLocalDate>parse(new DateDescriptionParser());
        ui.displaySchedule(date);
    }
}
