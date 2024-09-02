/**
 * Command to add a new task to the task list.
 */
public class AddCommand extends Command {
  private Task task;

  /**
   * Constructor for AddCommand.
   *
   * @param task The task to be added.
   */
  public AddCommand(Task task) {
    this.task = task;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws EliException {
    tasks.addTask(task);
    storage.save(tasks);
  }
}
