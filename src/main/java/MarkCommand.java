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
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws EliException {
    tasks.mark(index);
    storage.save(tasks);
  }
}
