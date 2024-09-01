public class AddCommand extends Command {
  private Task task;

  public AddCommand(Task task) {
    this.task = task;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws EliException {
    tasks.addTask(task);
    storage.save(tasks);
  }
}
