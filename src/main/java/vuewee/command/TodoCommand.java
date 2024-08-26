package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.TaskListUI;
import vuewee.task.TodoTask;

class TodoCommand extends Command {
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    parser.parse(true);
    ui.addTask(new TodoTask(parser.getDescription()));
  }
}
