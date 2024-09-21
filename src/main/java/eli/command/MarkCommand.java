package eli.command;

import eli.storage.Storage;
import eli.task.TaskList;
import eli.ui.Ui;

/**
 * Command to Mark the task.
 */
public class MarkCommand extends Command {
  private int index;

  public MarkCommand(int index) {
    this.index = index;
  }

  /**
   * Constructor for MarkCommand.
   *
   * @return
   */
  @Override
  public String execute(TaskList tasks, Ui ui, Storage storage) {
    try {
      tasks.mark(index);
      storage.save(tasks);
      return Ui.displayAfterMarkTask(tasks, index);
    } catch (IllegalArgumentException e) {
      return "Error: " + e.getMessage();
    }
  }
}
