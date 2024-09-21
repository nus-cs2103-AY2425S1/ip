package eli.command;

import eli.storage.Storage;
import eli.task.Task;
import eli.task.TaskList;
import eli.ui.Ui;

import java.util.List;

/**
 * Command to find tasks containing a specific keyword in their description.
 */
public class FindCommand extends Command {
  private String keyword;

  /**
   * Constructor for FindCommand.
   *
   * @param keyword The keyword to search for in task descriptions.
   */
  public FindCommand(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public String execute(TaskList tasks, Ui ui, Storage storage) {
    List<Task> listOfMatchingTasks = tasks.findTasksByKeyword(keyword);
    return Ui.showFindResults(listOfMatchingTasks);
  }
}
