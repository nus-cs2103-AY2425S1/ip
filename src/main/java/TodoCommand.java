public class TodoCommand extends Command {
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    parser.parse(true);
    ui.addTask(new TodoTask(parser.getDescription()));
  }
}
