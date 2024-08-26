package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.TaskListUI;

class ListCommand extends Command {
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    ui.displayTasks();
  }
}
