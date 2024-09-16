package eli.command;

import eli.storage.Storage;
import eli.task.TaskList;
import eli.ui.Ui;

/**
 * Command to list the application.
 */
public class ListCommand extends Command {

  /**
   * Constructor for ListCommand.
   *
   * @return
   */
  @Override
  public String execute(TaskList tasks, Ui ui, Storage storage) {
    try {
      tasks.list();
      return Ui.displayAfterListTask(tasks);
    } catch (IllegalArgumentException e) {
      return "Error: " + e.getMessage();
    }
  }
}