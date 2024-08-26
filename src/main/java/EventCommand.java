public class EventCommand extends Command {
  public void execute(TaskListUI ui, TaskList taskList, CommandParser parser) {
    CommandOption<TaskLocalDate> fromOption = new CommandOption<TaskLocalDate>("from", "fromDate yyyy-mm-dd",
        TaskLocalDate::parse);
    CommandOption<TaskLocalDate> toOption = new CommandOption<TaskLocalDate>("to", "toDate yyyy-mm-dd",
        TaskLocalDate.createParserWithFrom(na -> fromOption.getParsedValue()));
    parser.parse(true, false, fromOption, toOption);
    ui.addTask(new EventTask(parser.getDescription(), fromOption.getParsedValue(),
        toOption.getParsedValue()));
  }
}
