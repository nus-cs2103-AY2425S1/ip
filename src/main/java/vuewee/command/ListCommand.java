package vuewee.command;

import vuewee.parser.CommandParser;
import vuewee.task.TaskList;
import vuewee.TaskListUI;

/**
 * The ListCommand class represents a command to list all tasks.
 * It extends the Command class and implements the execute method.
 */
class ListCommand extends Command {
  /**
   * Executes the list command by displaying all tasks in the UI.
   *
   * @param ui       the TaskListUI object used to display tasks
   * @param taskList the TaskList object containing the tasks
   * @param parser   the CommandParser object used to parse commands
   */
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    ui.displayTasks();
  }
}
