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
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    tasks.unmark(index);
    storage.save(tasks);
  }
}
