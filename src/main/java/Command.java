public abstract class Command {
  public abstract void execute(TaskListUI ui, TaskList taskList, CommandParser parser);
}
