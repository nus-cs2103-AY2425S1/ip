package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.TaskListUI;

public abstract class Command {
  public abstract void execute(TaskListUI ui, TaskList taskList, CommandParser parser);
}
