package eli.command;

import eli.storage.Storage;
import eli.task.TaskList;
import eli.ui.Ui;

/**
 * Command to Unmark the task.
 */
public class UnmarkCommand extends Command {
  private int index;

  /**
   * Constructor for Unmark Command.
   */
  public UnmarkCommand(int index) {
    this.index = index;
  }

  @Override
  public String execute(TaskList tasks, Ui ui, Storage storage) {

    try {
      tasks.unmark(index);
      storage.save(tasks);
      return Ui.displayAfterUnmarkTask(tasks, index);
    } catch (IllegalArgumentException e) {
      return "Error: " + e.getMessage();
    }
  }
}
