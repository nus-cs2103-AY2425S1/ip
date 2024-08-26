public class DeadlineCommand extends Command {
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    CommandOption<TaskLocalDate> byOption = new CommandOption<TaskLocalDate>("by", "date",
        TaskLocalDate::parse);
    parser.parse(true, false, byOption);
    ui.addTask(new DeadlineTask(parser.getDescription(),
        byOption.getParsedValue()));
  }
}
