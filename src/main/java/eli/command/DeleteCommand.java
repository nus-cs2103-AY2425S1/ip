package eli.command;

import eli.exception.EliException;
import eli.storage.Storage;
import eli.task.Task;
import eli.task.TaskList;
import eli.ui.Ui;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
  private int index;

  /**
   * Constructor for DeleteCommand.
   *
   * @param index The index of the task to be deleted.
   */
  public DeleteCommand(int index) {
    this.index = index;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws EliException {
    tasks.delete(index);
    storage.save(tasks);
  }
}
