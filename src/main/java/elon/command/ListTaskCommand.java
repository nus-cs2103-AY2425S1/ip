package elon.command;

import elon.Storage;
import elon.Ui;
import elon.task.TaskList;
import java.io.IOException;

/** Represents a command to list all tasks in the task list. */
public class ListTaskCommand extends Command {

  /**
   * Executes the list task command by returning a message from the Ui that lists all tasks in the
   * task list.
   *
   * @param list the task list containing tasks to be displayed
   * @param ui the user interface to interact with the user
   * @param storage the storage
   * @return a string representing the list of tasks
   * @throws IOException if an input or output error occurs
   */
  @Override
  public String execute(TaskList list, Ui ui, Storage storage) throws IOException {
    return ui.listTasks(list);
  }
}
