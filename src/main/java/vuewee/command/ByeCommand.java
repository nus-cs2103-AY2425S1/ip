package vuewee.command;

import vuewee.EndProgramException;
import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.TaskListUI;

class ByeCommand extends Command {
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    throw new EndProgramException();
  }
}
