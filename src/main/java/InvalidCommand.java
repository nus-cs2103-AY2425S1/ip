public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(CommandType.INVALID);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printError("Invalid Command. Valid commands are: \n" +
                "list, todo, deadline, event, mark, unmark, bye");
    }
}
