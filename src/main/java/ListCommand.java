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