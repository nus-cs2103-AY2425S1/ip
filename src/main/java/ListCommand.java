public class ListCommand extends Command {
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    ui.displayTasks();
  }
}
