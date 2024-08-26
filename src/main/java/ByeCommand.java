public class ByeCommand extends Command {
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    throw new EndProgramException();
  }
}
