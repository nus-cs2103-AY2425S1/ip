public class UnmarkCommand extends Command {
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    parser.parse(true, true);
    ui.markTask(parser.getIntParam(), false);
  }
}
