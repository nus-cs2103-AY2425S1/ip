package eli.command;

import eli.storage.Storage;
import eli.task.Task;
import eli.task.TaskList;
import eli.ui.Ui;

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
  public String execute(TaskList tasks, Ui ui, Storage storage) {
    assert task != null : "Task to be added cannot be null";
    assert tasks.getTaskListLength() < 100 : "Task list is already full!!!";

    try {
      tasks.addTask(task);
      storage.save(tasks);
      return Ui.displayAfterAddTask(task);
    } catch (IllegalArgumentException e) {
      return "Error: " + e.getMessage();
    }
  }
}
