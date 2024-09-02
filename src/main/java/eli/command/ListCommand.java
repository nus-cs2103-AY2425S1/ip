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
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    tasks.list();
  }
}