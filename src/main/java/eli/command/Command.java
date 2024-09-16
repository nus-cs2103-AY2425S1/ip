package eli.command;

import eli.storage.Storage;
import eli.task.TaskList;
import eli.ui.Ui;

/**
 * Abstract class representing a Command that can be executed.
 * Concrete command classes should extend this class and implement the execute method.
 */
public abstract class Command {
  protected boolean isExit;

  /**
   * Constructor for Command.
   */
  public Command() {
    this.isExit = false;
  }

  /**
   * Executes the command.
   *
   * @param tasks   The TaskList containing all tasks.
   * @param ui      The Ui object to handle user interactions.
   * @param storage The Storage object to handle file operations.
   * @return
   */
  public abstract String execute(TaskList tasks, Ui ui, Storage storage);

  /**
   * Returns whether this command is an exit command.
   *
   * @return true if this command is an exit command, false otherwise.
   */
  public boolean isExit() {
    return isExit;
  }
}
